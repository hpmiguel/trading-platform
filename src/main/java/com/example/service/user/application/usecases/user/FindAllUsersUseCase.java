package com.example.service.user.application.usecases.user;

import com.example.service.user.domain.user.User;

import java.util.Collection;

public interface FindAllUsersUseCase {

    Collection<User> fetchAllPersisted();
}
