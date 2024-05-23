package com.example.service.trading.simplearchitecture.services.models;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class TradeDto {

    private final Integer id;

    private final Integer sellOrderId;
    private final Integer buyOrderId;

    private final Double price;

    private final Integer quantity;

}
