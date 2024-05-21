package com.example.service.trading.application.ports.persistence.security;

import com.example.service.trading.domain.security.Security;

import java.util.Optional;

public interface WriteSecurityPort {

    Security saveNew(Security security);

    Optional<Security> update(Security security);

    void deleteById(Integer securityId);
}
