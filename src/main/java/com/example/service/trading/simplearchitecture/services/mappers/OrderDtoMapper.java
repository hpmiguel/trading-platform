package com.example.service.trading.simplearchitecture.services.mappers;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.infrastructure.annotations.Mapper;
import com.example.service.trading.simplearchitecture.services.models.OrderDto;

@Mapper
public class OrderDtoMapper {

    OrderDtoMapper() {
        super();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .price(order.getPrice())
                .type(order.getType())
                .fulfilled(order.getFulfilled())
                .quantity(order.getQuantity())
                .build();
    }

    public Order toDomainFromDto(OrderDto orderDto) {
        return Order.builder()
                .userId(orderDto.getUserId())
                .securityId(orderDto.getSecurityId())
                .price(orderDto.getPrice())
                .type(orderDto.getType())
                .fulfilled(orderDto.getFulfilled())
                .quantity(orderDto.getQuantity())
                .build();
    }

    public Order toDomainFromDto(Integer orderId, OrderDto orderDto) {
        return Order.builder()
                .id(orderId)
                .userId(orderDto.getUserId())
                .securityId(orderDto.getSecurityId())
                .price(orderDto.getPrice())
                .type(orderDto.getType())
                .fulfilled(orderDto.getFulfilled())
                .quantity(orderDto.getQuantity())
                .build();
    }

}
