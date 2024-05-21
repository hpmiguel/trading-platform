package com.example.service.trading.infrastructure.adapters.api.models.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private final String username;

    private final String password;

}
