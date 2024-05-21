package com.example.service.trading.application.ports.persistence.user;

import com.example.service.trading.domain.user.User;

import java.util.Optional;

public interface WriteUserPort {

    User saveNew(User user);

    Optional<User> update(User user);

    void deleteById(Integer userId);
}
