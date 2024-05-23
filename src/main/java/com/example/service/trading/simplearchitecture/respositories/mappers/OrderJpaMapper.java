package com.example.service.trading.simplearchitecture.respositories.mappers;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.infrastructure.adapters.persistence.models.SecurityData;
import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import com.example.service.trading.infrastructure.annotations.Mapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;

@Mapper
public class OrderJpaMapper {

    OrderJpaMapper() {
        super();
    }

    public OrderData toJpaEntity(Order order) {
        UserData userData = UserData.builder().id(order.getUserId()).build();
        SecurityData securityData = SecurityData.builder().id(order.getSecurityId()).build();
        return OrderData.builder()
                .id(order.getId())
                .user(userData)
                .security(securityData)
                .type(order.getType())
                .fulfilled(order.getFulfilled())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }

    public OrderData toJpaEntity(Order order, OrderData persistedOrderData) {
        UserData userData = UserData.builder().id(order.getUserId()).build();
        SecurityData securityData = SecurityData.builder().id(order.getSecurityId()).build();
        return persistedOrderData.toBuilder()
                .id(order.getId())
                .user(userData)
                .security(securityData)
                .type(order.getType())
                .fulfilled(order.getFulfilled())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }

    public Order toDomain(OrderData orderData) {
        return Order.builder()
                .id(orderData.getId())
                .securityId(orderData.getSecurity().getId())
                .userId(orderData.getUser().getId())
                .type(orderData.getType())
                .fulfilled(orderData.getFulfilled())
                .price(orderData.getPrice())
                .quantity(orderData.getQuantity())
                .build();
    }

}
