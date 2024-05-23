package com.example.service.trading.simplearchitecture.respositories;

import com.example.service.trading.simplearchitecture.respositories.models.TradeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeData, Integer> { }