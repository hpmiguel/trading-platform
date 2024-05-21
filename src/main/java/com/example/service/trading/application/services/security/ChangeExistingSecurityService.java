package com.example.service.trading.application.services.security;

import com.example.service.trading.application.ports.persistence.security.WriteSecurityPort;
import com.example.service.trading.application.usecases.security.ChangeExistingSecurityUseCase;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
class ChangeExistingSecurityService implements ChangeExistingSecurityUseCase {

    private final WriteSecurityPort writeSecurityPort;

    public ChangeExistingSecurityService(WriteSecurityPort writeSecurityPort) {
        this.writeSecurityPort = writeSecurityPort;
    }

    @Override
    public Security updateSecurity(Security security) {
        ObjectValidator.validate(security);

        return writeSecurityPort.update(security).orElseThrow(EntityNotFoundException::new);
    }
}
