package com.example.service.trading.application.service.user;

import com.example.service.trading.application.ports.persistence.user.ReadUserPort;
import com.example.service.trading.application.ports.persistence.user.WriteUserPort;
import com.example.service.trading.application.services.user.DeleteUserByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import static com.example.service.trading.utils.DataFaker.fakeUserId;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class DeleteUsersByIdServiceTest {

    @InjectMocks
    private DeleteUserByIdService deleteUsersByIdService;

    @Mock
    private WriteUserPort writeUserPort;

    @Mock
    private ReadUserPort readUserPort;

    @Test
    public void shouldThrowException_whenUserIdIsInvalidDomainObject() {
        UserId userId = UserId.of(null);

        Assertions.assertThatThrownBy(() -> deleteUsersByIdService.deleteById(userId))
                .isInstanceOf(ConstraintViolationException.class);
        Mockito.verify(writeUserPort, Mockito.never()).deleteById(Mockito.any());
        Mockito.verify(readUserPort, Mockito.never()).existsUserById(Mockito.any());
    }

    @Test
    public void shouldThrowException_whenUserDoesNotExists() {
        UserId userId = fakeUserId();

        Mockito.when(readUserPort.existsUserById(userId)).thenReturn(false);

        Assertions.assertThatThrownBy(() -> deleteUsersByIdService.deleteById(userId))
            .isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(writeUserPort, Mockito.never()).deleteById(Mockito.any());
        Mockito.verify(readUserPort, Mockito.times(1)).existsUserById(Mockito.any());
    }

    @Test
    public void shouldApplyDeleteAction_whenUserExistsAndValidUserIdProvided() {
        UserId userId = fakeUserId();

        Mockito.when(readUserPort.existsUserById(userId)).thenReturn(true);

        deleteUsersByIdService.deleteById(userId);

        Mockito.verify(writeUserPort, Mockito.times(1)).deleteById(Mockito.any());
        Mockito.verify(readUserPort, Mockito.times(1)).existsUserById(Mockito.any());
    }

}