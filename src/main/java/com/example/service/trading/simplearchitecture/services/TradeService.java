package com.example.service.trading.simplearchitecture.services;

import com.example.service.trading.domain.trade.Trade;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.simplearchitecture.respositories.TradeRepository;
import com.example.service.trading.simplearchitecture.respositories.mappers.TradeJpaMapper;
import com.example.service.trading.simplearchitecture.respositories.models.TradeData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class TradeService {

    private final TradeRepository tradeRepository;
    private final TradeJpaMapper tradeJpaMapper;

    public TradeService(TradeRepository tradeRepository, TradeJpaMapper tradeJpaMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeJpaMapper = tradeJpaMapper;
    }

    public Optional<Trade> fetchById(Integer tradeId) {
        return tradeRepository.findById(tradeId)
                .map(tradeJpaMapper::toDomain);
    }

    public List<Trade> fetchAll() {
        return tradeRepository.findAll()
                .stream()
                .map(tradeJpaMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    public Trade saveNew(Trade trade) {
        TradeData tradeData = tradeJpaMapper.toJpaEntity(trade);
        TradeData tradeSaved = tradeRepository.save(tradeData);
        return tradeJpaMapper.toDomain(tradeSaved);
    }

}
