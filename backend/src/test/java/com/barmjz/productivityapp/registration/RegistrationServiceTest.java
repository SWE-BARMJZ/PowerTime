package com.barmjz.productivityapp.registration;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private UserRepoService userService;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private PasswordEncoder passwordEncoder;

    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationService(userService, emailValidator, passwordEncoder);
    }

    @Test
    void ThrowsWhenEmailNotValid(){
        // Given
        String email = "dummy";
        String password = "password";
        String firstName = "Jack";
        String lastName = "Grealish";
        RegistrationRequest registrationRequest = new RegistrationRequest(email,
                password,
                firstName,
                lastName);
        given(emailValidator.test(email)).willReturn(false);

        // Then
        assertThatThrownBy( () ->  registrationService.create(registrationRequest))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("invalid email");
        verify(userService, never()).saveUser(any(), any());

    }

    @Test
    void canRegisterUser(){
        // Given
        String email = "dummy@gmail.com";
        String password = "password";
        String firstName = "Jack";
        String lastName = "Grealish";
        RegistrationRequest registrationRequest = new RegistrationRequest(email,
                password,
                firstName,
                lastName);
        given(emailValidator.test(email)).willReturn(true);
        User user = new User(email,
                password,
                firstName,
                lastName);
        // When
        registrationService.create(registrationRequest);
        // Then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).saveUser(userArgumentCaptor.capture(), any());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);

    }
}
