package com.example.service.trading.simplearchitecture.respositories.mappers;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.infrastructure.annotations.Mapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;

@Mapper
public class OrderJpaMapper {

    OrderJpaMapper() {
        super();
    }

    public OrderData toJpaEntity(Order order) {
        return OrderData.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .securityId(order.getSecurityId())
                .type(order.getType())
                .fulfilled(order.getFulfilled())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }

    public OrderData toJpaEntity(Order order, OrderData persistedOrderData) {
        return persistedOrderData.toBuilder()
                .id(order.getId())
                .type(order.getType())
                .fulfilled(order.getFulfilled())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }

    public Order toDomain(OrderData orderData) {
        return Order.builder()
                .id(orderData.getId())
                .type(orderData.getType())
                .fulfilled(orderData.getFulfilled())
                .price(orderData.getPrice())
                .quantity(orderData.getQuantity())
                .build();
    }

}
