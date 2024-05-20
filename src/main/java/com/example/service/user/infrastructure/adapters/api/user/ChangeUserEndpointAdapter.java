package com.example.service.user.infrastructure.adapters.api.user;

import com.example.service.user.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.user.infrastructure.adapters.api.models.SaveUserBodyDto;
import com.example.service.user.infrastructure.adapters.api.models.UserDto;
import com.example.service.user.application.ports.api.user.ChangeUserEndpointPort;
import com.example.service.user.application.usecases.user.ChangeExistingUserUseCase;
import com.example.service.user.application.usecases.user.DeleteUsersByIdUseCase;
import com.example.service.user.application.usecases.user.SubmitNewUserUseCase;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.annotations.Adapter;

@Adapter
public class ChangeUserEndpointAdapter implements ChangeUserEndpointPort {

    private final SubmitNewUserUseCase submitNewUserUseCase;

    private final ChangeExistingUserUseCase changeExistingUserUseCase;

    private final DeleteUsersByIdUseCase deleteUsersByIdUseCase;

    private final UserDtoMapper userDtoMapper;

    ChangeUserEndpointAdapter(SubmitNewUserUseCase submitNewUserUseCase,
                              ChangeExistingUserUseCase changeExistingUserUseCase,
                              DeleteUsersByIdUseCase deleteUsersByIdUseCase,
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
