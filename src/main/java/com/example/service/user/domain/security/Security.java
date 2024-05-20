package com.example.service.user.domain.security;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
@Getter(AccessLevel.PACKAGE)
@Setter
public class Security {

    private final Integer id;

    @NotNull
    private final String name;

}
