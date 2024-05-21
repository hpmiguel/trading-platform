package com.example.service.trading.application.services.security;

import com.example.service.trading.application.ports.persistence.security.ReadSecurityPort;
import com.example.service.trading.application.ports.persistence.security.WriteSecurityPort;
import com.example.service.trading.application.usecases.security.SubmitNewSecurityUseCase;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
class SubmitNewSecurityService implements SubmitNewSecurityUseCase {

    private final WriteSecurityPort writeSecurityPort;

    private final ReadSecurityPort readSecurityPort;

    SubmitNewSecurityService(WriteSecurityPort writeSecurityPort,
                             ReadSecurityPort readSecurityPort) {
        this.writeSecurityPort = writeSecurityPort;
        this.readSecurityPort = readSecurityPort;
    }

    @Override
    public Security saveSecurity(Security security) {
        ObjectValidator.validate(security);

        if(readSecurityPort.existsSecurityByName(security)) {
            throw new IllegalArgumentException("Security duplicated...");
        }
        return writeSecurityPort.saveNew(security);
    }
}
