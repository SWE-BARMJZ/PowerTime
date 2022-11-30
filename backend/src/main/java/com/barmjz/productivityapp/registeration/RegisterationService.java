package com.barmjz.productivityapp.registeration;

import com.barmjz.productivityapp.User.User;
import com.barmjz.productivityapp.User.UserRepo;
import com.barmjz.productivityapp.User.UserRepoService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterationService {

    private final UserRepoService userRepoService;
    private final EmailValidator emailValidator;

    private final PasswordEncoder bCryptPasswordEncoder;
    public String create(RegisterationRequest registerationRequest) {
        boolean isValid = emailValidator.test(registerationRequest.getEmail());
        if(!isValid)
            throw new IllegalStateException("invalid email");
        return userRepoService.saveUser(new User(
                registerationRequest.getEmail(),
                registerationRequest.getPassword(),
                "n",
                "n",
                "ROLE_USER"
                ),
                bCryptPasswordEncoder
        );
    }
}
