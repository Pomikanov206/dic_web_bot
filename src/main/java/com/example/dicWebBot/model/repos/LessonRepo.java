package com.example.dicWebBot.model.repos;

import com.example.dicWebBot.model.domain.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepo extends CrudRepository<Lesson, Integer> {
}
