package com.example.service.trading.infrastructure.adapters.api.mappers;

import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.annotations.Mapper;

@Mapper
public class UserDtoMapper {

    UserDtoMapper() {
        super();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User toDomainFromSaveBody(SaveUserBodyDto saveUserBodyDto) {
        return User.builder()
                .username(saveUserBodyDto.getUsername())
                .password(saveUserBodyDto.getPassword())
                .build();
    }

    public User toDomainFromSaveBody(Integer userId, SaveUserBodyDto saveUserBodyDto) {
        return User.builder()
                .id(userId)
                .username(saveUserBodyDto.getUsername())
                .password(saveUserBodyDto.getPassword())
                .build();
    }
}
