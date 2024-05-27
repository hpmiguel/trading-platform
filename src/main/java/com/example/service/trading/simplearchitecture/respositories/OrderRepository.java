package com.example.service.trading.simplearchitecture.respositories;


import com.example.service.trading.domain.order.OrderType;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> {

    OrderData findFirstByType(OrderType type, Sort sort);

    List<OrderData> findAllByTypeAndSecurityIdAndFulfilledAndPriceGreaterThanEqual(
            OrderType type, Integer securityId, Boolean fulfilled, Double price);

}
