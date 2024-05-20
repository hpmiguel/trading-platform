package com.example.service.user.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
public class User implements Serializable {

    private final Integer id;

    @NotNull
    private final String username;

    @NotNull
    private final String password;

}
