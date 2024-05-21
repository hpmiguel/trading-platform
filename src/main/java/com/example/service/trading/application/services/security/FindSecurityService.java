package com.example.service.trading.application.services.security;

import com.example.service.trading.application.ports.persistence.security.ReadSecurityPort;
import com.example.service.trading.application.usecases.security.FindAllSecuritiesUseCase;
import com.example.service.trading.application.usecases.security.FindSecurityByIdUseCase;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
class FindSecurityService implements FindSecurityByIdUseCase, FindAllSecuritiesUseCase {

    private final ReadSecurityPort readSecurityPort;

    FindSecurityService(ReadSecurityPort readSecurityPort) {
        this.readSecurityPort = readSecurityPort;
    }

    @Override
    public Security findById(Integer securityId) {
        ObjectValidator.validate(securityId);
        return readSecurityPort.fetchById(securityId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Collection<Security> fetchAllPersisted() {
        return readSecurityPort.fetchAll();
    }

}
