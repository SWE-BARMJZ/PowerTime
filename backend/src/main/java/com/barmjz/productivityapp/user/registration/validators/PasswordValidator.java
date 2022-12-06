package com.barmjz.productivityapp.user.registration.validators;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements Predicate<String> {

    /*
        This regex matches a password with:
        - at least 8 characters
        - at least one upper case letter
        - at least one lower case letter
        - at least one number
        - at least one Special Character !@#$%^&*()\-__+.
    */
    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-__+.])[a-zA-Z\\d[!@#$%^&*()\\-__+.]]{8,}$");

    @Override
    public boolean test(String password) {
        return VALID_PASSWORD_REGEX
                .matcher(password)
                .find();
    }
}
