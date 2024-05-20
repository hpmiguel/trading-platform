package com.example.service.user.application.ports.api.user;

import com.example.service.user.infrastructure.adapters.api.models.SaveUserBodyDto;
import com.example.service.user.infrastructure.adapters.api.models.UserDto;

public interface ChangeUserEndpointPort {

    UserDto saveUser(SaveUserBodyDto saveUserBodyDto);

    UserDto updateUser(Integer id, SaveUserBodyDto saveUserBodyDto);

    void deleteUser(Integer userId);

}
