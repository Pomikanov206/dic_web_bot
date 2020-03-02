package com.example.dicWebBot.model.repos;

import com.example.dicWebBot.model.domain.Days;
import com.example.dicWebBot.model.domain.Lesson;
import com.example.dicWebBot.model.domain.WeekType;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Integer> {
    Optional<Lesson> findById(Integer id);
    List<Lesson> findAll();

    List<Lesson> findByGroupName(String groupName);
    List<Lesson> findByGroupNameAndNumber(String groupName, int number);
    List<Lesson> findByGroupNameAndDay(String groupName, Days day);

    List<Lesson> findByGroupNameAndDayAndWeekType(String groupName, Days day, WeekType weekType, Sort sort);
    List<Lesson> findByGroupNameAndDayAndWeekTypeBetween(String groupName, Days day, WeekType current, WeekType universal);
    List<Lesson> findByGroupNameAndDayAndWeekTypeBetween(String groupName, Days day, WeekType current, WeekType universal, Sort sort);
    List<Lesson> findByGroupNameAndDayAndWeekTypeOrWeekType(String groupName, Days day, WeekType current, WeekType universal, Sort sort);
}
