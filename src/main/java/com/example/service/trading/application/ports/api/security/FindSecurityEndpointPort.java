package com.example.service.trading.application.ports.api.security;


import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;

import java.util.Collection;

public interface FindSecurityEndpointPort {

    Collection<SecurityDto> fetchAllSecurities();

    SecurityDto fetchSecurityById(Integer securityId);
}
