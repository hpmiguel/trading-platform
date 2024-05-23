package com.example.service.trading.infrastructure.adapters.api.models.user;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;

    private String password;

}
