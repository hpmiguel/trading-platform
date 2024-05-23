package com.example.service.trading.simplearchitecture.respositories.models;

import com.example.service.trading.domain.order.OrderType;
import com.example.service.trading.infrastructure.adapters.persistence.models.SecurityData;
import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "orders")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserData user;

    @ManyToOne
    @JoinColumn(name = "securityId", nullable = false)
    private SecurityData security;

    private OrderType type;

    private Double price;

    private Integer quantity;

    private Boolean fulfilled;
}
