package com.example.service.user.application.usecases.user;

import com.example.service.user.domain.user.User;

public interface ChangeExistingUserUseCase {

    User updateUser(User user);
}
