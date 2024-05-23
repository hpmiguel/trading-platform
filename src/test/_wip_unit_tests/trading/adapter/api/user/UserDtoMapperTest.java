package com.example.service.trading.adapter.api.user;

import com.example.service.trading.infrastructure.adapters.api.mappers.UserDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.service.trading.utils.DataFaker.fakeSaveUserBodyDto;
import static com.example.service.trading.utils.DataFaker.fakeUser;
import static com.example.service.trading.utils.DataFaker.fakeUserIdAsInt;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    private UserDtoMapper userDtoMapper;

    @Test
    public void shouldMapToDtoFromDomain() {
        User user = fakeUser();
        UserDto userDto = userDtoMapper.toDto(user);

        Assertions.assertThat(userDto.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void shouldMapToDomainFromDtoWithoutUserId() {
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User user = userDtoMapper.toDomainFromSaveBody(saveUserBodyDto);

        Assertions.assertThat(user.getId()).isNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(saveUserBodyDto.getUsername());
        Assertions.assertThat(user.getPassword()).isEqualTo(saveUserBodyDto.getPassword());
    }

    @Test
    public void shouldMapToDomainFromDtoWithUserId() {
        Integer userId = fakeUserIdAsInt();
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();

        User user = userDtoMapper.toDomainFromSaveBody(userId, saveUserBodyDto);

        Assertions.assertThat(user.getId()).isEqualTo(userId);
        Assertions.assertThat(user.getUsername()).isEqualTo(saveUserBodyDto.getUsername());
        Assertions.assertThat(user.getPassword()).isEqualTo(saveUserBodyDto.getPassword());
    }
}