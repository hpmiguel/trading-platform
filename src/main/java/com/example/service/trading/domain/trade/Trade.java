package com.example.service.trading.domain.trade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
public class Trade implements Serializable {

    private Integer tradeId;

    private Integer sellOrderId;
    private Integer buyOrderId;

    @NotNull
    private final Float price;

    @NotNull
    private final Integer quantity;

}
