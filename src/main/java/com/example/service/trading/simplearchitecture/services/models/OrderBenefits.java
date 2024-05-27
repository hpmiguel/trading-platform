package com.example.service.trading.simplearchitecture.services.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderBenefits {
    Integer orderId;
    Double benefits;
}
