package com.example.service.trading.application.ports.api.user;

import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;

public interface ChangeUserEndpointPort {

    UserDto saveUser(SaveUserBodyDto saveUserBodyDto);

    UserDto updateUser(Integer id, SaveUserBodyDto saveUserBodyDto);

    void deleteUser(Integer userId);

}
