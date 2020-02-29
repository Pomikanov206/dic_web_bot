package com.example.dicWebBot.web.controller;

import com.example.dicWebBot.model.domain.Days;
import com.example.dicWebBot.model.domain.Lesson;
import com.example.dicWebBot.model.repos.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private LessonRepo lessonRepo;

    @GetMapping("/add-lesson")
    public String lessonForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "add-lesson";
    }

    @PostMapping("/add-lesson")
    public String lessonSubmit(@ModelAttribute Lesson lesson, Model model) {
        lessonRepo.save(lesson);
        model.addAttribute("lesson", lesson);
        return "lesson-list";
    }

    @GetMapping("/timetable")
    public String timeTable(Model model) {
        //List<Lesson> lessonList = lessonRepo.findByGroupName("КС-19-2-11");
        //List<Lesson> lessonList = lessonRepo.findByGroupNameAndDay("КС-19-2-11", Days.MONDAY);
        List<Lesson> lessonList = lessonRepo.findAll();
        model.addAttribute("lessons",lessonList);
        return "timetable";
    }

    @GetMapping("/delete-lesson/{id}")
    public String deleteLesson(@PathVariable Integer id, Model model) {
        lessonRepo.deleteById(id);
        List<Lesson> lessonList = lessonRepo.findAll();
        model.addAttribute("lessons",lessonList);
        return "timetable";
    }

    @GetMapping("/update-lesson/{id}")
    public String updateLesson(@PathVariable Integer id, Model model) {
        Lesson lesson = lessonRepo.findById(id).get();
        model.addAttribute("lesson", lesson);
        return "update-lesson";
    }

    //@PostMapping("/update-lesson")
    @RequestMapping(value = "/update-lesson", method = RequestMethod.POST)
    public String updateLesson(@ModelAttribute Lesson lesson,  Model  model) {

        lessonRepo.save(lesson);
        List<Lesson> lessonList = lessonRepo.findAll();
        model.addAttribute("lessons",lessonList);
        return "timetable";
    }
}
