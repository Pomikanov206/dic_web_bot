package com.example.dicWebBot.model.repos;

import com.example.dicWebBot.model.domain.Days;
import com.example.dicWebBot.model.domain.Lesson;
import com.example.dicWebBot.model.domain.WeekType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepo extends CrudRepository<Lesson, Integer> {
    Optional<Lesson> findById(Integer id);
    List<Lesson> findAll();

    List<Lesson> findByGroupName(String groupName);
    List<Lesson> findByGroupNameAndNumber(String groupName, int number);
    List<Lesson> findByGroupNameAndDay(String groupName, Days day);

    List<Lesson> findByGroupNameAndDayAndWeekType(String groupName, Days day, WeekType weekType);
    List<Lesson> findByGroupNameAndDayAndWeekTypeBetween(String groupName, Days day, WeekType current, WeekType universal);
}
