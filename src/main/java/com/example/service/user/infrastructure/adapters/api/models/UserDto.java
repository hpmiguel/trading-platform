package com.example.service.user.infrastructure.adapters.api.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private final String username;

    private final String password;

}
