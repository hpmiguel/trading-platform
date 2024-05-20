package com.example.service.user.domain.order;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
@Getter(AccessLevel.PACKAGE)
@Setter
public class Order {

    private final Integer id;

    private final Integer userId;
    private final Integer securityId;

    @NotNull
    private final OrderType type;

    @NotNull
    private final Float price;

    @NotNull
    private final Integer quantity;

    @NotNull
    private final Boolean fulfilled;

}
