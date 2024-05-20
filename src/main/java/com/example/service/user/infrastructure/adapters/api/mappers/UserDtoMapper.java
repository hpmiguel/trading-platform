package com.example.service.user.infrastructure.adapters.api.mappers;

import com.example.service.user.infrastructure.adapters.api.models.UserDto;
import com.example.service.user.infrastructure.adapters.api.models.SaveUserBodyDto;
import com.example.service.user.domain.user.User;
import com.example.service.user.infrastructure.annotations.Mapper;

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
