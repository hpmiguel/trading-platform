package com.example.service.user.application.services.user;

import com.example.service.user.application.ports.persistence.user.ReadUserPort;
import com.example.service.user.application.ports.persistence.user.WriteUserPort;
import com.example.service.user.application.usecases.user.DeleteUsersByIdUseCase;
import com.example.service.user.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
class DeleteUsersByIdService implements DeleteUsersByIdUseCase {

    private final WriteUserPort writeUserPort;

    private final ReadUserPort readUserPort;

    DeleteUsersByIdService(WriteUserPort writeUserPort,
                           ReadUserPort readUserPort) {
        this.writeUserPort = writeUserPort;
        this.readUserPort = readUserPort;
    }

    @Override
    public void deleteById(Integer userId) {
        ObjectValidator.validate(userId);

        if (!readUserPort.existsUserById(userId)) {
            throw new IllegalArgumentException("User missed on the repository, not able to delete it...");
        }

        writeUserPort.deleteById(userId);
    }
}
