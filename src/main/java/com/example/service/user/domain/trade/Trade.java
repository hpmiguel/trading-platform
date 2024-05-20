package com.example.service.user.domain.trade;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
@Getter(AccessLevel.PACKAGE)
@Setter
public class Trade {

    private Integer tradeId;

    private Integer sellOrderId;
    private Integer buyOrderId;

    @NotNull
    private final Float price;

    @NotNull
    private final Integer quantity;

}
