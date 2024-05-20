package com.example.service.user.application.ports.persistence.user;

import com.example.service.user.domain.user.User;

import java.util.Optional;

public interface WriteUserPort {

    User saveNew(User user);

    Optional<User> update(User user);

    void deleteById(Integer userId);
}
