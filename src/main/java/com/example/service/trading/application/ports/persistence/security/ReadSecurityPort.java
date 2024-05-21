package com.example.service.trading.application.ports.persistence.security;

import java.util.List;
import java.util.Optional;

import com.example.service.trading.domain.security.Security;

public interface ReadSecurityPort {
    Boolean existsSecurityByName(Security security);

    Boolean existsSecurityById(Integer securityId);

    Optional<Security> fetchById(Integer securityId);

    List<Security> fetchAll();
}
