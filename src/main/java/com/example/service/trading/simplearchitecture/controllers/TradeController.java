package com.example.service.trading.simplearchitecture.controllers;

import com.example.service.trading.domain.trade.Trade;
import com.example.service.trading.simplearchitecture.services.TradeService;
import com.example.service.trading.simplearchitecture.services.mappers.TradeDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;
    private final TradeDtoMapper tradeDtoMapper;

    TradeController(TradeService tradeService, TradeDtoMapper tradeDtoMapper) {
        this.tradeService = tradeService;
        this.tradeDtoMapper = tradeDtoMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Trade> fetchAllTrades() {
        return tradeService.fetchAll();
    }

}
