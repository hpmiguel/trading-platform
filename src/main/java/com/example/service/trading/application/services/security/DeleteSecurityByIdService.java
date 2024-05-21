package com.example.service.trading.application.services.security;

import com.example.service.trading.application.ports.persistence.security.ReadSecurityPort;
import com.example.service.trading.application.ports.persistence.security.WriteSecurityPort;
import com.example.service.trading.application.usecases.security.DeleteSecurityByIdUseCase;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
class DeleteSecurityByIdService implements DeleteSecurityByIdUseCase {

    private final WriteSecurityPort writeSecurityPort;

    private final ReadSecurityPort readSecurityPort;

    DeleteSecurityByIdService(WriteSecurityPort writeSecurityPort,
                              ReadSecurityPort readSecurityPort) {
        this.writeSecurityPort = writeSecurityPort;
        this.readSecurityPort = readSecurityPort;
    }

    @Override
    public void deleteById(Integer securityId) {
        ObjectValidator.validate(securityId);

        if (!readSecurityPort.existsSecurityById(securityId)) {
            throw new IllegalArgumentException("Security missed on the repository, not able to delete it...");
        }

        writeSecurityPort.deleteById(securityId);
    }
}
