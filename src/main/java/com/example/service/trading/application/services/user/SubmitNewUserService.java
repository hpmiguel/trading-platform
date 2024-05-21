package com.example.service.trading.application.services.user;

import com.example.service.trading.application.ports.persistence.user.ReadUserPort;
import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.application.usecases.user.SubmitNewUserUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
public class SubmitNewUserService implements SubmitNewUserUseCase {

    private final WriteUserPort writeUserPort;

    private final ReadUserPort readUserPort;

    SubmitNewUserService(WriteUserPort writeUserPort,
                         ReadUserPort readUserPort) {
        this.writeUserPort = writeUserPort;
        this.readUserPort = readUserPort;
    }

    @Override
    public User saveUser(User user) {
        ObjectValidator.validate(user);

        if(readUserPort.existsUserByName(user)) {
            throw new IllegalArgumentException("User duplicated...");
        }
        return writeUserPort.saveNew(user);
    }
}
