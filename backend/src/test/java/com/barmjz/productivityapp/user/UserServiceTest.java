package com.barmjz.productivityapp.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordencoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo);
    }


    @Test
    void canSaveUser() {
        // Given
        String email = "dummy@gmail.com";
        String password = "password";
        String firstName = "Jack";
        String lastName = "Grealish";
        User user = new User(email,
                password,
                firstName,
                lastName
                );
        // When
        userService.saveUser(user, passwordencoder);

        // Then
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepo).getUserByEmail(stringArgumentCaptor.capture());
        String capturedEmail = stringArgumentCaptor.getValue();
        assertThat(capturedEmail).isEqualTo(email);

        verify(passwordencoder).encode(stringArgumentCaptor.capture());
        String capturedPassword = stringArgumentCaptor.getValue();
        assertThat(capturedPassword).isEqualTo(password);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepo).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(email);
        assertThat(capturedUser.getPassword()).isNotEqualTo(password);
        assertThat(capturedUser.getFirstName()).isEqualTo(firstName);
        assertThat(capturedUser.getLastName()).isEqualTo(lastName);
    }

    @Test
    void willThrowWhenUserAlreadyExists() {
        // Given
        String email = "dummy@gmail.com";
        String password = "password";
        String firstName = "Jack";
        String lastName = "Grealish";
        User user = new User(email,
                password,
                firstName,
                lastName
        );
        Optional<User> t = Optional.of(user);

        given(userRepo.getUserByEmail(email)).willReturn(t);

        // Then
        assertThatThrownBy(() -> userService.saveUser(user, passwordencoder))
                .isInstanceOf( IllegalStateException.class)
                .hasMessageContaining("email already exists");

        verify(passwordencoder, never()).encode(any());
        verify(userRepo, never()).save(any());

    }

    @Test
    void canLoadUser() {
        // Given
        String email = "dummy@gmail.com";
        String password = "password";
        String firstName = "Jack";
        String lastName = "Grealish";
        User user = new User(email,
                password,
                firstName,
                lastName
        );
        Optional<User> t = Optional.of(user);
        given(userRepo.getUserByEmail(email)).willReturn(t);
        // Then
        assertThat(userService.loadUserByUsername(email)).isInstanceOf(SecurityUser.class);
    }

    @Test
    void ThrowsWhenUserNotFound() {
        // Given
        String email = "dummy@gmail.com";
        Optional<User> t = Optional.empty();
        given(userRepo.getUserByEmail(email)).willReturn(t);
        // Then
        assertThatThrownBy(() -> userService.loadUserByUsername(email)).
                isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("Username not found:" + email);
    }


}
