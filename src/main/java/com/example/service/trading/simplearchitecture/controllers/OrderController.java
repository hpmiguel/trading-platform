package com.example.service.trading.simplearchitecture.controllers;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.simplearchitecture.services.OrderService;
import com.example.service.trading.simplearchitecture.services.mappers.OrderDto;
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

//    @PutMapping("/{order_id}")
//    @ResponseStatus(HttpStatus.OK)
//    public OrderDto updateOrder(@PathVariable("order_id") int orderId,
//                              @RequestBody @Valid SaveOrderBodyDto saveOrderBodyDto) {
//        return changeOrderEndpointAdapter.updateOrder(orderId, saveOrderBodyDto);
//    }

//    @GetMapping("/{order_id}")
//    @ResponseStatus(HttpStatus.OK)
//    public OrderDto fetchOrderById(@PathVariable("order_id") Integer orderId) {
//        return findOrderEndpointAdapter.fetchOrderById(orderId);
//    }

//    @DeleteMapping("/{order_id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteOrderById(@PathVariable("order_id") Integer orderId) {
//        changeOrderEndpointAdapter.deleteOrder(orderId);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Order> fetchAllOrders() {
        return orderService.fetchAll();
    }

}
