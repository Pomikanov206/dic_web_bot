package com.example.dicWebBot.model.repos;

import com.example.dicWebBot.model.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findByChatId(String chatId);
}
