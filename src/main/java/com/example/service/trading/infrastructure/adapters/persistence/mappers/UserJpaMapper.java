package com.example.service.trading.infrastructure.adapters.persistence.mappers;

import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import com.example.service.trading.domain.user.User;
import com.example.service.trading.infrastructure.annotations.Mapper;

@Mapper
public class UserJpaMapper {

    UserJpaMapper() {
        super();
    }

    public UserData toJpaEntity(User user) {
        return UserData.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public UserData toJpaEntity(User user, UserData persistedUserData) {
        return persistedUserData.toBuilder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User toDomain(UserData userData) {
        return User.builder()
                .id(userData.getId())
                .username(userData.getUsername())
                .password(userData.getPassword())
                .build();
    }

}
