package com.example.service.trading.adapter.api.user;


import com.example.service.trading.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.application.usecases.user.ChangeExistingUserUseCase;
import com.example.service.trading.application.usecases.user.DeleteUserByIdUseCase;
import com.example.service.trading.application.usecases.user.SubmitNewUserUseCase;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.adapters.api.user.ChangeUserEndpointAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.service.trading.utils.DataFaker.fakeSaveUserBodyDto;
import static com.example.service.trading.utils.DataFaker.fakeUser;
import static com.example.service.trading.utils.DataFaker.fakeUserDto;
import static com.example.service.trading.utils.DataFaker.fakeUserId;
import static com.example.service.trading.utils.DataFaker.fakeUserIdAsInt;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ChangeUserEndpointAdapterTest {

    @InjectMocks
    private ChangeUserEndpointAdapter changeUserEndpointAdapter;

    @Mock
    private SubmitNewUserUseCase submitNewUserUseCase;

    @Mock
    private ChangeExistingUserUseCase changeExistingUserUseCase;

    @Mock
    private DeleteUserByIdUseCase deleteUsersByIdUseCase;

    @Mock
    private UserDtoMapper userDtoMapper;

    @Test
    public void shouldSaveNewUser_returningUserData() {
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User userTransformedFromSaveUserDto = fakeUser();
        User userCreated = fakeUser();
        UserDto userDtoConvertedFromSaveUser = fakeUserDto();

        Mockito.when(userDtoMapper.toDomainFromSaveBody(saveUserBodyDto)).thenReturn(userTransformedFromSaveUserDto);
        Mockito.when(submitNewUserUseCase.saveUser(userTransformedFromSaveUserDto)).thenReturn(userCreated);
        Mockito.when(userDtoMapper.toDto(userCreated)).thenReturn(userDtoConvertedFromSaveUser);

        UserDto savedUserDto = changeUserEndpointAdapter.saveUser(saveUserBodyDto);
        Assertions.assertThat(savedUserDto).isEqualTo(userDtoConvertedFromSaveUser);
    }

    @Test
    public void shouldUpdateUser_returningUserData() {
        int userId = fakeUserIdAsInt();
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User userTransformedFromSaveUserDto = fakeUser();
        User userCreated = fakeUser();
        UserDto userDtoConvertedFromSaveUser = fakeUserDto();

        Mockito.when(userDtoMapper.toDomainFromSaveBody(userId, saveUserBodyDto)).thenReturn(userTransformedFromSaveUserDto);
        Mockito.when(changeExistingUserUseCase.updateUser(userTransformedFromSaveUserDto)).thenReturn(userCreated);
        Mockito.when(userDtoMapper.toDto(userCreated)).thenReturn(userDtoConvertedFromSaveUser);

        UserDto savedUserDto = changeUserEndpointAdapter.updateUser(userId, saveUserBodyDto);
        Assertions.assertThat(savedUserDto).isEqualTo(userDtoConvertedFromSaveUser);
    }

    @Test
    public void shouldDeleteUser_returningVoid() {
        Integer userId = fakeUserId();

        changeUserEndpointAdapter.deleteUser(userId);

        Mockito.verify(deleteUsersByIdUseCase).deleteById(userId);
    }

}