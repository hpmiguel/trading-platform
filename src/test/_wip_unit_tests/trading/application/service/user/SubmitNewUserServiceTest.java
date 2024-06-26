package com.example.service.trading.application.service.user;

import com.example.service.trading.application.ports.persistence.user.ReadUserPort;
import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.application.services.user.SubmitNewUserService;
import com.example.service.trading.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import static com.example.service.trading.utils.DataFaker.fakeUserBuilder;
import static com.example.service.trading.utils.DataFaker.fakeUserId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SubmitNewUserServiceTest {

    @InjectMocks
    private SubmitNewUserService submitNewUserService;

    @Mock
    private WriteUserPort writeUserPort;

    @Mock
    private ReadUserPort readUserPort;

    @Test
    public void shouldThrowException_whenUserDomainObjectIsInvalid() {
        User user = fakeUserBuilder().fullName(null).build();

        Assertions.assertThatThrownBy(() -> submitNewUserService.saveUser(user))
                .isInstanceOf(ConstraintViolationException.class);
        Mockito.verify(writeUserPort, Mockito.never()).saveNew(Mockito.any());
        Mockito.verify(readUserPort, Mockito.never()).existsUserByName(Mockito.any());
    }

    @Test
    public void shouldThrowException_whenUserNameIsAlreadyPersisted_AvoidingDuplicates() {
        User user = fakeUserBuilder().build();

        Mockito.when(readUserPort.existsUserByName(user)).thenReturn(true);

        Assertions.assertThatThrownBy(() -> submitNewUserService.saveUser(user))
                .isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(writeUserPort, Mockito.never()).saveNew(Mockito.any());
        Mockito.verify(readUserPort, Mockito.times(1)).existsUserByName(Mockito.any());
    }

    @Test
    public void shouldProcessOnUserCreation_whenUserIsValid_AndNotDuplicated() {
        User user = fakeUserBuilder().build();
        User savedUser = user.toBuilder().id(fakeUserId()).build();

        Mockito.when(readUserPort.existsUserByName(user)).thenReturn(false);
        Mockito.when(writeUserPort.saveNew(user)).thenReturn(savedUser);

        User saveUser = submitNewUserService.saveUser(user);
        Assertions.assertThat(saveUser).isEqualTo(savedUser);

        Mockito.verify(writeUserPort, Mockito.times(1)).saveNew(Mockito.any());
        Mockito.verify(readUserPort, Mockito.times(1)).existsUserByName(Mockito.any());
    }

}