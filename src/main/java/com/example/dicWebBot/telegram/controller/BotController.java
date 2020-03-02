package com.example.dicWebBot.telegram.controller;

import com.example.dicWebBot.model.domain.Days;
import com.example.dicWebBot.model.domain.Lesson;
import com.example.dicWebBot.model.domain.User;
import com.example.dicWebBot.model.ILesson;
import com.example.dicWebBot.model.LessonDataBase;
import com.example.dicWebBot.model.domain.WeekType;
import com.example.dicWebBot.model.repos.LessonRepo;
import com.example.dicWebBot.model.repos.UserRepo;
import com.example.dicWebBot.telegram.view.Bot;
import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BotController {
    private Bot view;
    //private IUser userDataBase;
    private ILesson lessonDataBase;
    private UserRepo userRepo;
    private LessonRepo lessonRepo;

    public BotController(Bot view, UserRepo userRepo, LessonRepo lessonRepo) {
        this.view = view;
        this.userRepo = userRepo;
        this.lessonRepo = lessonRepo;
        lessonDataBase = new LessonDataBase();
    }

    public void commandHandler(String chatId, String command) {
        String courseRegex = "\\d Курс";
        Pattern pattern = Pattern.compile(courseRegex);
        //  \\w{2}-\\d{2}-\\d/?(\\d{2})?
        Matcher matcher = pattern.matcher(command);

        if(command.equals("/start"))
            view.sendCoursePick(chatId);
        else if(matcher.matches()) {
            // ------- !!!!!!!!!!!!!!!!!!!!!!_______--
            pattern = Pattern.compile("\\d");
            matcher = pattern.matcher(command);

            matcher.find();

            int courseNum = Integer.parseInt(matcher.group());

            //Добавить список групп из внешнего источника
            String[] groupList;

            switch (courseNum) {
                case 1: groupList = new String[]{"КС-19-1", "ЕА-19-1"};break;
                case 2: groupList = new String[]{"КС-18-1", "КС-19-2-11", "ЕА-18-1"};break;
                case 3: groupList = new String[]{"КС-17-1", "КС-18-2-11", "ЕА-17-1"};break;
                case 4: groupList = new String[]{"КС-16-1", "КС-17-2-11", "ЕА-16-1"};break;
                default: groupList = new String[]{"КС-18-1", "КС-19-2-11", "ЕА-18-1"};
            }
            view.sendGroupPick(chatId, groupList);
        }

        String groupRegex = "\\D{2}-\\d{2}-\\d-?(\\d{2})?";
        pattern = Pattern.compile(groupRegex);
        //  \\w{2}-\\d{2}-\\d/?(\\d{2})?
        matcher = pattern.matcher(command);

        if (matcher.matches()){         //Допилить маску надо бы
            userRepo.save(new User(chatId,command));
            System.out.println("User Added");
            view.sendMenuPick(chatId);
        }

        //Main menu
        if(command.equals("Розклад")) {
            //System.out.println("Группа: " + userDataBase.readUser(chatId));
            String group = userRepo.findByChatId(chatId).get(0).getUserGroup();
            System.out.println("Группа: " + group);
            //String tibeTable = lessonDataBase.readLesson(userRepo.findByChatId(chatId).get(0).getUserGroup(),null);
            //-------->>  КОСТЫЛИЩЕ!!!!
            String result = "";
            int dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            int weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
//            List<Lesson> lessonList = lessonRepo.findByGroupNameAndDayAndWeekTypeBetween(
//                    group, Days.getDay(dayNumber), WeekType.DENOMINATOR, WeekType.NONE);

            Days day = Days.getDay(dayNumber);
            WeekType currentWeek = WeekType.getType(weekNumber);
            /*List<Lesson> lessonList = lessonRepo.findByGroupNameAndDayAndWeekTypeBetween(
                    group,
                    Days.TUESDAY,
                    WeekType.NONE, WeekType.DENOMINATOR,
                    Sort.by("number"));
            */

            List<Lesson> lessonList = lessonRepo.findByGroupNameAndDayAndWeekType(
                    group,
                    day,
                    currentWeek,
                    Sort.by("number"));

            lessonList.addAll(lessonRepo.findByGroupNameAndDayAndWeekType(
                    group,
                    day,
                    currentWeek,
                    Sort.by("number")
            ));

            lessonList.sort(new Comparator<Lesson>() {
                @Override
                public int compare(Lesson o1, Lesson o2) {
                    return o1.getNumber() - o2.getNumber();
                }
            });

            for (Lesson lesson: lessonList
                 ) {
                result += lesson.getNumber() + ":" +
                        lesson.getLessonName() + " (" +
                        lesson.getTeacher() + ") - " +
                        lesson.getRoom() + "\n";
            }
            view.sendTimeTable(chatId, result);
        }
        else if(command.equals("Экзамены"))
            view.sendExams(chatId,"Exams 1.0");
        else if(command.equals("Замены"))
            view.sendChanges(chatId, "Changes 1.0");
        else if(command.equals("Другое"))
            view.sendOtherPick(chatId);

        //Other menu
        if(command.equals("Консультации"))
            ;
        else if(command.equals("Кружки"))
            ;
        else if(command.equals("О колледже"))
            ;

        //About college menu
        if(command.equals("Информация"))
            ;
        else if(command.equals("Местоположение"))
            ;
    }

    public void addLesson(){
        lessonDataBase.createLesson();
    }
    public String getTimeTable() {
        return "TimeTable";
    }

}
