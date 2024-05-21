package com.example.service.trading.application.usecases.user;

import com.example.service.trading.domain.user.User;

import java.util.Collection;

public interface FindAllUsersUseCase {

    Collection<User> fetchAllPersisted();
}
