package com.example.service.trading.simplearchitecture.services;

import com.example.service.trading.domain.order.Order;
import com.example.service.trading.domain.order.OrderType;
import com.example.service.trading.infrastructure.adapters.persistence.mappers.SecurityJpaMapper;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.simplearchitecture.respositories.OrderRepository;
import com.example.service.trading.simplearchitecture.respositories.mappers.OrderJpaMapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;
import com.example.service.trading.simplearchitecture.services.mappers.OrderDtoMapper;
import com.example.service.trading.simplearchitecture.services.models.OrderBenefits;
import com.example.service.trading.simplearchitecture.services.models.OrderDto;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    public Order saveNew(Order order) {
        OrderData orderData = orderJpaMapper.toJpaEntity(order);
        OrderData orderSaved = orderRepository.save(orderData);
        this.tradingAlgorithm();
        return orderJpaMapper.toDomain(orderSaved);
    }

    //// PRIVATE

    private void tradingAlgorithm() {
        // 1. Get first sell order, if not found break
        // 2. Get buy orders not fulfilled with same security and price not less sell price
        // 3. Calculate benefits each one (quantity * price)
        // 4. Choose order the highest result
        // 5. Update buy order quantity (total - bought)
        // 6. Update sell order quantity (total - sold)
        // 7. If sell order not fulfilled repeat from step 2
        //    Else create trade
        // 8. Repeat all process until no sell orders
        // NOTE: Call Services / Use cases from other entities (composition pattern)

        Sort sort = Sort.by("id").ascending();
        OrderData firstSellOrder = this.orderRepository.findFirstByType(OrderType.SELL, sort);
        if (firstSellOrder != null) {
            List<OrderData> buyOrders = this.orderRepository
                    .findAllByTypeAndSecurityIdAndFulfilledAndPriceGreaterThanEqual(
                            OrderType.BUY, firstSellOrder.getSecurity().getId(), false,
                            firstSellOrder.getPrice());

            // Calculating costs, Could be done with a query
            List<OrderBenefits> ordersBenefits = buyOrders.stream()
                    .map(o -> OrderBenefits.builder().orderId(o.getId())
                            .benefits(o.getQuantity() * o.getPrice()).build())
                    .collect(Collectors.toList());
            OrderBenefits highestBenefits = ordersBenefits.stream().max(Comparator.comparing(OrderBenefits::getBenefits))
                    .orElse(null);

            OrderData highestBuyOrder = buyOrders.stream().filter(o -> {
                assert highestBenefits != null;
                return Objects.equals(o.getId(), highestBenefits.getOrderId());
            }).findFirst().orElse(null);

            if (highestBuyOrder != null) {
                buildUpdatedOrder(firstSellOrder, highestBuyOrder);
                buildUpdatedOrder(highestBuyOrder, firstSellOrder);
            }
        }
    }

    private void buildUpdatedOrder(OrderData firstSellOrder, OrderData highestBuyOrder) {
        OrderData updateBuyOrder = OrderData.builder()
                .id(highestBuyOrder.getId())
                .price(highestBuyOrder.getPrice())
                .type(highestBuyOrder.getType())
                .user(highestBuyOrder.getUser())
                .security(highestBuyOrder.getSecurity())
                .quantity(highestBuyOrder.getQuantity() <= firstSellOrder.getQuantity() ?
                        0 : (highestBuyOrder.getQuantity() - firstSellOrder.getQuantity()))
                .fulfilled(highestBuyOrder.getQuantity()<=firstSellOrder.getQuantity())
                .build();
        this.orderRepository.save(updateBuyOrder);
    }

}
