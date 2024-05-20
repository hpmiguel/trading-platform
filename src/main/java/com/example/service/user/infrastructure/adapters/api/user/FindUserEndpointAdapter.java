package com.example.service.user.infrastructure.adapters.api.user;

import com.example.service.user.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.user.infrastructure.adapters.api.models.UserDto;
import com.example.service.user.application.ports.api.user.FindUserEndpointPort;
import com.example.service.user.application.usecases.user.FindAllUsersUseCase;
import com.example.service.user.application.usecases.user.FindUserByIdUseCase;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.annotations.Adapter;

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
