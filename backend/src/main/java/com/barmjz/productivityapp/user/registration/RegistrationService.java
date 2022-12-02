package com.barmjz.productivityapp.user.registration;

import com.barmjz.productivityapp.user.registration.validators.EmailValidator;
import com.barmjz.productivityapp.user.registration.validators.PasswordValidator;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                    registrationRequest.getEmail(),
                    registrationRequest.getPassword(),
                    registrationRequest.getFirstName(),
                    registrationRequest.getLastName()
                ),
                bCryptPasswordEncoder
        );
    }

    private void validate(RegistrationRequest request) {
        boolean emailIsValid = emailValidator.test(request.getEmail());
        boolean passwordIsValid = passwordValidator.test(request.getPassword());
        boolean namesAreValid = !request.getFirstName().isEmpty() &&
                                !request.getLastName().isEmpty();

        if (!emailIsValid) throw new IllegalStateException("invalid email");
        if (!passwordIsValid) throw new IllegalStateException("weak password");
        if (!namesAreValid) throw new IllegalStateException("first and last name can't be empty");
    }
}
