package com.barmjz.productivityapp.user.registration;

import com.barmjz.productivityapp.user.registration.validators.EmailValidator;
import com.barmjz.productivityapp.user.registration.validators.PasswordValidator;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PasswordEncoder bCryptPasswordEncoder;

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
        boolean emailIsValid = emailValidator.test(request.email());
        boolean passwordIsValid = passwordValidator.test(request.password());
        boolean namesAreValid = !request.firstName().isEmpty() &&
                                !request.lastName().isEmpty();

        if (!emailIsValid) throw new IllegalStateException("invalid email");
        if (!passwordIsValid) throw new IllegalStateException("weak password");
        if (!namesAreValid) throw new IllegalStateException("first and last name can't be empty");
    }
}
