package com.example.service.trading.domain.order;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
public class Order implements Serializable {

    private final Integer id;

    private final Integer userId;
    private final Integer securityId;

    @NotNull
    private final OrderType type;

    @NotNull
    private final Double price;

    @NotNull
    private final Integer quantity;

    @NotNull
    private final Boolean fulfilled;

}
