package com.example.service.trading.domain.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
public class Security implements Serializable {

    private final Integer id;

    @NotNull
    private final String name;

}
