package com.example.service.trading.application.ports.persistence.user;

import com.example.service.trading.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface ReadUserPort {
    Boolean existsUserByName(User user);

    Boolean existsUserById(Integer userId);

    Optional<User> fetchById(Integer userId);

    List<User> fetchAll();
}
