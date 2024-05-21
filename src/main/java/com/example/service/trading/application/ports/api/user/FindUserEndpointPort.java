package com.example.service.trading.application.ports.api.user;

import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;

import java.util.Collection;

public interface FindUserEndpointPort {

    Collection<UserDto> fetchAllUsers();

    UserDto fetchUserById(Integer userId);
}
