package com.example.service.trading.infrastructure.adapters.persistence.user;

import com.example.service.trading.infrastructure.adapters.persistence.mappers.UserJpaMapper;
import com.example.service.trading.infrastructure.repositories.UserRepository;
import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.annotations.Adapter;

import java.util.Optional;


@Adapter
public class WriteUserAdapter implements WriteUserPort {

    private final UserRepository userRepository;

    private final UserJpaMapper userJpaMapper;

    public WriteUserAdapter(UserRepository userRepository, UserJpaMapper userJpaMapper) {
        this.userRepository = userRepository;
        this.userJpaMapper = userJpaMapper;
    }

    @Override
    public User saveNew(User user) {
        UserData userData = userJpaMapper.toJpaEntity(user);
        UserData userSaved = userRepository.save(userData);
        return userJpaMapper.toDomain(userSaved);
    }

    @Override
    public Optional<User> update(User user) {
        return userRepository.findById(user.getId())
                .map(persistedUserData -> userJpaMapper.toJpaEntity(user, persistedUserData))
                .map(userRepository::save)
                .map(userJpaMapper::toDomain);
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }


}
