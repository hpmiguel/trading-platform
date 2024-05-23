package com.example.service.trading.simplearchitecture.respositories.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "trades")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TradeData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sellOrderId")
    private OrderData sellOrder;

    @ManyToOne
    @JoinColumn(name = "buyOrderId")
    private OrderData buyOrder;

    private Double price;

    private Integer quantity;
}
