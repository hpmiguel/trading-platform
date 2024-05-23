package com.example.service.trading.simplearchitecture.respositories;


import com.example.service.trading.simplearchitecture.respositories.models.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> { }
