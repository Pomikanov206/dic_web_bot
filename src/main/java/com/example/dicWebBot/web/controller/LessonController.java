package com.example.dicWebBot.web.controller;

import com.example.dicWebBot.model.domain.Days;
import com.example.dicWebBot.model.domain.Lesson;
import com.example.dicWebBot.model.repos.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("timetable")
    public String timeTable(Model model) {
        List<Lesson> lessonList = lessonRepo.findByGroupName("КС-19-2-11");
        model.addAttribute("lessons",lessonList);
        return "timetable";
    }
}
