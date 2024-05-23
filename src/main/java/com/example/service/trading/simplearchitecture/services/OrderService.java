package com.example.service.trading.simplearchitecture.services;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.simplearchitecture.respositories.OrderRepository;
import com.example.service.trading.simplearchitecture.respositories.mappers.OrderJpaMapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;
import com.example.service.trading.simplearchitecture.services.mappers.OrderDtoMapper;
import com.example.service.trading.simplearchitecture.services.models.OrderDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderJpaMapper orderJpaMapper;
    private final OrderDtoMapper orderDtoMapper;

    public OrderService(OrderRepository orderRepository, OrderJpaMapper orderJpaMapper, OrderDtoMapper orderDtoMapper) {
        this.orderRepository = orderRepository;
        this.orderJpaMapper = orderJpaMapper;
        this.orderDtoMapper = orderDtoMapper;
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

    public Order updateOrder(Integer id, OrderDto orderDto) {
        Order order = orderDtoMapper.toDomainFromDto(id, orderDto);
        return orderRepository.findById(order.getId())
                .map(persistedOrderData -> orderJpaMapper.toJpaEntity(order, persistedOrderData))
                .map(orderRepository::save)
                .map(orderJpaMapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }

    // TODO
    // private void tradingAlgorithm() {
        // 1. Get first sell order, if not found break
        // 2. Get buy orders not fulfilled with same security and quantity not get over sell quantity
        // 3. Calculate cost/benefits each one (quantity * price)
        // 4. Choose order the highest result
        // 5. Update buy order like fulfilled
        // 6. Update sell order quantity (total - sold)
        // 7. If sell order not fulfilled repeat from step 2
        //    Else create trade
        // 8. Repeat all process until no sell orders
        // NOTE: Call Services / Use cases from other entities (composition pattern)
    // }

    public Order saveNew(Order order) {
        OrderData orderData = orderJpaMapper.toJpaEntity(order);
        OrderData orderSaved = orderRepository.save(orderData);
        // this.tradingAlgorithm();
        return orderJpaMapper.toDomain(orderSaved);
    }

}
