package com.example.service.trading.application.services.user;

import com.example.service.trading.application.ports.persistence.user.ReadUserPort;
import com.example.service.trading.application.usecases.user.FindAllUsersUseCase;
import com.example.service.trading.application.usecases.user.FindUserByIdUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class FindUserService implements FindUserByIdUseCase, FindAllUsersUseCase {

    private final ReadUserPort readUserPort;

    FindUserService(ReadUserPort readUserPort) {
        this.readUserPort = readUserPort;
    }

    @Override
    public User findById(Integer userId) {
        ObjectValidator.validate(userId);
        return readUserPort.fetchById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Collection<User> fetchAllPersisted() {
        return readUserPort.fetchAll();
    }

}
