package com.example.service.user.infrastructure.adapters.persistence.user;

import com.example.service.user.application.ports.persistence.user.ReadUserPort;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.adapters.persistence.mappers.UserJpaMapper;
import com.example.service.user.infrastructure.repositories.UserRepository;
import com.example.service.user.infrastructure.annotations.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
class ReadUserAdapter implements ReadUserPort {

    private final UserRepository userRepository;

    private final UserJpaMapper userJpaMapper;

    public ReadUserAdapter(UserRepository userRepository, UserJpaMapper userJpaMapper) {
        this.userRepository = userRepository;
        this.userJpaMapper = userJpaMapper;
    }

    @Override
    public Boolean existsUserByName(User user) {
        return !userRepository.findByUsername(user.getUsername())
                .isEmpty();
    }

    @Override
    public Boolean existsUserById(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public Optional<User> fetchById(Integer userId) {
        return userRepository.findById(userId)
                .map(userJpaMapper::toDomain);
    }

    @Override
    public List<User> fetchAll() {
        return userRepository.findAll()
                .stream()
                .map(userJpaMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }
}
