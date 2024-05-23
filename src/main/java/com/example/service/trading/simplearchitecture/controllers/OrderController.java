package com.example.service.trading.simplearchitecture.controllers;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.simplearchitecture.services.OrderService;
import com.example.service.trading.simplearchitecture.services.models.OrderDto;
import com.example.service.trading.simplearchitecture.services.mappers.OrderDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    OrderController(OrderService orderService, OrderDtoMapper orderDtoMapper) {
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderDtoMapper.toDomainFromDto(orderDto);
        return this.orderService.saveNew(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Order> fetchAllOrders() {
        return orderService.fetchAll();
    }

}
