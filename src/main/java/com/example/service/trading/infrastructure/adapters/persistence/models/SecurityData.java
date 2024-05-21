package com.example.service.trading.infrastructure.adapters.persistence.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "securities")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SecurityData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
}
