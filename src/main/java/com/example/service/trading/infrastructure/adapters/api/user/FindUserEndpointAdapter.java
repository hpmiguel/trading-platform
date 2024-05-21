package com.example.service.trading.infrastructure.adapters.api.user;

import com.example.service.trading.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.application.ports.api.user.FindUserEndpointPort;
import com.example.service.trading.application.usecases.user.FindAllUsersUseCase;
import com.example.service.trading.application.usecases.user.FindUserByIdUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.annotations.Adapter;

import java.util.Collection;
import java.util.stream.Collectors;

@Adapter
public class FindUserEndpointAdapter implements FindUserEndpointPort {

    private final FindAllUsersUseCase findAllUsersUseCase;

    private final FindUserByIdUseCase findUserByIdUseCase;

    private final UserDtoMapper userDtoMapper;

    FindUserEndpointAdapter(FindAllUsersUseCase findAllUsersUseCase,
                            FindUserByIdUseCase findUserByIdUseCase,
                            UserDtoMapper userDtoMapper) {
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserDto fetchUserById(Integer userId) {
        User foundUser = findUserByIdUseCase.findById(userId);
        return userDtoMapper.toDto(foundUser);
    }

    @Override
    public Collection<UserDto> fetchAllUsers() {
        return findAllUsersUseCase.fetchAllPersisted()
                .stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
