package com.example.service.user.application.usecases.user;

import com.example.service.user.domain.user.User;

public interface FindUserByIdUseCase {

    User findById(Integer userId);
}
