package com.example.service.trading.application.services.user;

import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.application.usecases.user.ChangeExistingUserUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ChangeExistingUserService implements ChangeExistingUserUseCase {

    private final WriteUserPort writeUserPort;

    public ChangeExistingUserService(WriteUserPort writeUserPort) {
        this.writeUserPort = writeUserPort;
    }

    @Override
    public User updateUser(User user) {
        ObjectValidator.validate(user);

        return writeUserPort.update(user).orElseThrow(EntityNotFoundException::new);
    }
}
