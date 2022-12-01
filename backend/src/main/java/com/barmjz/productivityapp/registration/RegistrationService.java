package com.barmjz.productivityapp.registration;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepoService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepoService userRepoService;
    private final EmailValidator emailValidator;

    private final PasswordEncoder bCryptPasswordEncoder;
    public String create(RegistrationRequest registrationRequest) {
        boolean isValid = emailValidator.test(registrationRequest.getEmail());
        if(!isValid)
            throw new IllegalStateException("invalid email");
        return userRepoService.saveUser(new User(
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                "n",
                "n"
                ),
                bCryptPasswordEncoder
        );
    }
}
