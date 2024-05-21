package com.example.service.trading.infrastructure.adapters.api.user;

import com.example.service.trading.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.application.ports.api.user.ChangeUserEndpointPort;
import com.example.service.trading.application.usecases.user.ChangeExistingUserUseCase;
import com.example.service.trading.application.usecases.user.DeleteUserByIdUseCase;
import com.example.service.trading.application.usecases.user.SubmitNewUserUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.annotations.Adapter;

@Adapter
public class ChangeUserEndpointAdapter implements ChangeUserEndpointPort {

    private final SubmitNewUserUseCase submitNewUserUseCase;

    private final ChangeExistingUserUseCase changeExistingUserUseCase;

    private final DeleteUserByIdUseCase deleteUsersByIdUseCase;

    private final UserDtoMapper userDtoMapper;

    ChangeUserEndpointAdapter(SubmitNewUserUseCase submitNewUserUseCase,
                              ChangeExistingUserUseCase changeExistingUserUseCase,
                              DeleteUserByIdUseCase deleteUsersByIdUseCase,
                              UserDtoMapper userDtoMapper) {
        this.submitNewUserUseCase = submitNewUserUseCase;
        this.changeExistingUserUseCase = changeExistingUserUseCase;
        this.deleteUsersByIdUseCase = deleteUsersByIdUseCase;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserDto saveUser(SaveUserBodyDto saveUserBodyDto) {
        User user = userDtoMapper.toDomainFromSaveBody(saveUserBodyDto);
        User savedUser = submitNewUserUseCase.saveUser(user);
        return userDtoMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Integer id, SaveUserBodyDto saveUserBodyDto) {
        User user = userDtoMapper.toDomainFromSaveBody(id, saveUserBodyDto);
        User updatedUser = changeExistingUserUseCase.updateUser(user);
        return userDtoMapper.toDto(updatedUser);
    }


    @Override
    public void deleteUser(Integer userId) {
        deleteUsersByIdUseCase.deleteById(userId);
    }

}
