package com.example.service.trading.adapter.persistence.user;

import com.example.service.trading.infrastructure.adapters.persistence.mappers.UserJpaMapper;
import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import com.example.service.trading.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.service.trading.utils.DataFaker.fakeUserBuilder;
import static com.example.service.trading.utils.DataFaker.fakeUserData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(MockitoExtension.class)
class UserJpaMapperTest {

    @InjectMocks
    private UserJpaMapper userJpaMapper;

    @Test
    public void shouldMapToJpaEntityFromDomain_forNewUser() {
        User user = fakeUserBuilder().id(null).build();

        UserData userData = userJpaMapper.toJpaEntity(user);
        assertThat(userData.getId()).isNull();
        assertThat(userData.getFirstName()).isEqualTo(userFirstName.apply(user));
        assertThat(userData.getLastName()).isEqualTo(userLastName.apply(user));
        assertThat(userData.getPhone()).isEqualTo(userPhoneNumber.apply(user));
        assertThat(userData.getCreatedAt()).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
        assertThat(userData.getUpdatedAt()).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
    }

    @Test
    public void shouldMapToJpaEntityFromDomain_forExistingUser() {
        User user = fakeUserBuilder().build();

        UserData userData = userJpaMapper.toJpaEntity(user);
        assertThat(userData.getId()).isEqualTo(userIdAsInt.apply(user));
        assertThat(userData.getFirstName()).isEqualTo(userFirstName.apply(user));
        assertThat(userData.getLastName()).isEqualTo(userLastName.apply(user));
        assertThat(userData.getPhone()).isEqualTo(userPhoneNumber.apply(user));
        assertThat(userData.getCreatedAt()).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
        assertThat(userData.getUpdatedAt()).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
    }

    @Test
    public void shouldMapToJpaEntityFromDomain_overridingPersistingJpaEntity() {
        User user = fakeUserBuilder().id(null).build();
        UserData persistedUserData = fakeUserData();

        UserData userData = userJpaMapper.toJpaEntity(user, persistedUserData);
        assertThat(userData.getId()).isEqualTo(persistedUserData.getId());
        assertThat(userData.getFirstName()).isEqualTo(userFirstName.apply(user));
        assertThat(userData.getLastName()).isEqualTo(userLastName.apply(user));
        assertThat(userData.getPhone()).isEqualTo(userPhoneNumber.apply(user));
        assertThat(userData.getCreatedAt()).isEqualTo(persistedUserData.getCreatedAt());
        assertThat(userData.getUpdatedAt()).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
    }

    @Test
    public void shouldMapJpaEntityToDomain() {
        UserData userData = fakeUserData();

        User user = userJpaMapper.toDomain(userData);

        assertThat(userIdAsInt.apply(user)).isEqualTo(userData.getId());
        assertThat(userFirstName.apply(user)).isEqualTo(userData.getFirstName());
        assertThat(userLastName.apply(user)).isEqualTo(userData.getLastName());
        assertThat(userPhoneNumber.apply(user)).isEqualTo(userData.getPhone());
    }

}