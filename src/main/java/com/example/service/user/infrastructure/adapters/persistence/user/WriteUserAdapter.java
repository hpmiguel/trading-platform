package com.example.service.user.infrastructure.adapters.persistence.user;

import com.example.service.user.infrastructure.adapters.persistence.mappers.UserJpaMapper;
import com.example.service.user.infrastructure.repositories.UserRepository;
import com.example.service.user.infrastructure.adapters.persistence.models.UserData;
import com.example.service.user.application.ports.persistence.user.WriteUserPort;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.annotations.Adapter;

import java.util.Optional;


@Adapter
class WriteUserAdapter implements WriteUserPort {

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
