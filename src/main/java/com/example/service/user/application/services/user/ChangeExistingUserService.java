package com.example.service.user.application.services.user;

import com.example.service.user.application.ports.persistence.user.WriteUserPort;
import com.example.service.user.application.usecases.user.ChangeExistingUserUseCase;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
class ChangeExistingUserService implements ChangeExistingUserUseCase {

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
