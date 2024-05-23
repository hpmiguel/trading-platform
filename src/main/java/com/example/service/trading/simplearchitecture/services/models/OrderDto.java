package com.example.service.trading.simplearchitecture.services.models;

import com.example.service.trading.domain.order.OrderType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class OrderDto {

    private final Integer id;

    private final Integer userId;
    private final Integer securityId;

    private final OrderType type;

    private final Double price;

    private final Integer quantity;

    private final Boolean fulfilled;

}
