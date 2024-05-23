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

    private Integer id;

    private Integer sellOrderId;
    private Integer buyOrderId;

    @NotNull
    private final Double price;

    @NotNull
    private final Integer quantity;

}
