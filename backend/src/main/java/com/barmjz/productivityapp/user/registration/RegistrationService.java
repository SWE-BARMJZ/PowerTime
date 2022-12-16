package com.barmjz.productivityapp.user.registration;

import com.barmjz.productivityapp.user.registration.token.ConfirmationToken;
import com.barmjz.productivityapp.user.registration.token.ConfirmationTokenService;
import com.barmjz.productivityapp.user.registration.validators.EmailValidator;
import com.barmjz.productivityapp.user.registration.validators.PasswordValidator;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest registrationRequest) {
        validate(registrationRequest);

        return userService.saveUser(
                new User(
                    registrationRequest.email(),
                    registrationRequest.password(),
                    registrationRequest.firstName(),
                    registrationRequest.lastName()
                ),
                bCryptPasswordEncoder
        );
    }

    private void validate(RegistrationRequest request) {
        boolean namesNotNull = request.firstName() != null &&
                request.lastName() != null;
        if(!namesNotNull) throw new NullPointerException("first and last name can't be null");
        boolean emailIsValid = emailValidator.test(request.email());
        boolean passwordIsValid = passwordValidator.test(request.password());
        boolean namesAreValid = !request.firstName().isEmpty() &&
                                !request.lastName().isEmpty();

        if (!emailIsValid) throw new IllegalStateException("invalid email");
        if (!passwordIsValid) throw new IllegalStateException("weak password");
        if (!namesAreValid) throw new IllegalStateException("first and last name can't be empty");
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.verifyUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
