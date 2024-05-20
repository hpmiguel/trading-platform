package com.example.service.user.application.ports.api.user;

import com.example.service.user.infrastructure.adapters.api.models.UserDto;

import java.util.Collection;

public interface FindUserEndpointPort {

    Collection<UserDto> fetchAllUsers();

    UserDto fetchUserById(Integer userId);
}
