package com.example.service.trading.application.services.user;

import com.example.service.trading.application.ports.persistence.user.ReadUserPort;
import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.application.usecases.user.DeleteUserByIdUseCase;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdService implements DeleteUserByIdUseCase {

    private final WriteUserPort writeUserPort;

    private final ReadUserPort readUserPort;

    DeleteUserByIdService(WriteUserPort writeUserPort,
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
