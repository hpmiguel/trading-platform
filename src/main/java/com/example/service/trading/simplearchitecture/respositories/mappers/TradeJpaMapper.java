package com.example.service.trading.simplearchitecture.respositories.mappers;

import com.example.service.trading.domain.trade.Trade;
import com.example.service.trading.infrastructure.annotations.Mapper;
import com.example.service.trading.simplearchitecture.respositories.models.OrderData;
import com.example.service.trading.simplearchitecture.respositories.models.TradeData;

@Mapper
public class TradeJpaMapper {

    TradeJpaMapper() {
        super();
    }

    public TradeData toJpaEntity(Trade trade) {
        OrderData buyOrderData = OrderData.builder().id(trade.getBuyOrderId()).build();
        OrderData sellOrderData = OrderData.builder().id(trade.getSellOrderId()).build();
        return TradeData.builder()
                .id(trade.getId())
                .sellOrder(sellOrderData)
                .buyOrder(buyOrderData)
                .price(trade.getPrice())
                .quantity(trade.getQuantity())
                .build();
    }

    public TradeData toJpaEntity(Trade trade, TradeData persistedTradeData) {
        OrderData buyOrderData = OrderData.builder().id(trade.getBuyOrderId()).build();
        OrderData sellOrderData = OrderData.builder().id(trade.getSellOrderId()).build();
        return persistedTradeData.toBuilder()
                .id(trade.getId())
                .sellOrder(sellOrderData)
                .buyOrder(buyOrderData)
                .price(trade.getPrice())
                .quantity(trade.getQuantity())
                .build();
    }

    public Trade toDomain(TradeData tradeData) {
        return Trade.builder()
                .id(tradeData.getId())
                .sellOrderId(tradeData.getSellOrder().getId())
                .buyOrderId(tradeData.getBuyOrder().getId())
                .price(tradeData.getPrice())
                .quantity(tradeData.getQuantity())
                .build();
    }

}
