package com.example.service.trading.application.usecases.user;

import com.example.service.trading.domain.user.User;

public interface SubmitNewUserUseCase {

    User saveUser(User user);
}
