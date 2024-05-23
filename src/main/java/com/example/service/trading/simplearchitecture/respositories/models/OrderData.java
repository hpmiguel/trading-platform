package com.example.service.trading.simplearchitecture.respositories.models;

import com.example.service.trading.domain.order.OrderType;
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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Integer userId;

    private Integer securityId;

    private OrderType type;

    private Double price;

    private Integer quantity;

    private Boolean fulfilled;
}
