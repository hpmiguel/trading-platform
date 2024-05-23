package com.example.service.trading.simplearchitecture.services;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.simplearchitecture.respositories.OrderRepository;
import com.example.service.trading.simplearchitecture.respositories.mappers.OrderJpaMapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderJpaMapper orderJpaMapper;

    public OrderService(OrderRepository orderRepository, OrderJpaMapper orderJpaMapper) {
        this.orderRepository = orderRepository;
        this.orderJpaMapper = orderJpaMapper;
    }

    public Optional<Order> fetchById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderJpaMapper::toDomain);
    }

    public List<Order> fetchAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderJpaMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    public Order saveNew(Order order) {
        OrderData orderData = orderJpaMapper.toJpaEntity(order);
        OrderData orderSaved = orderRepository.save(orderData);
        return orderJpaMapper.toDomain(orderSaved);
    }

}
