package com.example.service.trading.infrastructure.adapters.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SaveUserBodyDto {

    private String username;

    private String password;

}
