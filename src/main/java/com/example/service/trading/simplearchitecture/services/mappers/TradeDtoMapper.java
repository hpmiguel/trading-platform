package com.example.service.trading.simplearchitecture.services.mappers;

import com.example.service.trading.domain.trade.Trade;
import com.example.service.trading.infrastructure.annotations.Mapper;
import com.example.service.trading.simplearchitecture.services.models.TradeDto;

@Mapper
public class TradeDtoMapper {

    TradeDtoMapper() {
        super();
    }

    public TradeDto toDto(Trade trade) {
        return TradeDto.builder()
                .buyOrderId(trade.getBuyOrderId())
                .sellOrderId(trade.getSellOrderId())
                .price(trade.getPrice())
                .quantity(trade.getQuantity())
                .build();
    }

    public Trade toDomainFromDto(TradeDto tradeDto) {
        return Trade.builder()
                .buyOrderId(tradeDto.getBuyOrderId())
                .sellOrderId(tradeDto.getSellOrderId())
                .price(tradeDto.getPrice())
                .quantity(tradeDto.getQuantity())
                .build();
    }
}
