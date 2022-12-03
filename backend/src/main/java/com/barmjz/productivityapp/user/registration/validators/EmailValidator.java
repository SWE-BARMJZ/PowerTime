package com.barmjz.productivityapp.user.registration.validators;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    /*
    The following restrictions are imposed in the email address' local part by using this regex:
        - It allows numeric values from 0 to 9.
        - Both uppercase and lowercase letters from a to z are allowed.
        - Allowed are underscore “_”, hyphen “-“, and dot “.”
        - Dot isn't allowed at the start and end of the local part.
        - Consecutive dots aren't allowed.
        - For the local part, a maximum of 64 characters are allowed.
    Restrictions for the domain part in this regular expression include:
        - It allows numeric values from 0 to 9.
        - We allow both uppercase and lowercase letters from a to z.
        - Hyphen “-” and dot “.” aren't allowed at the start and end of the domain part.
        - No consecutive dots.
    */
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    @Override
    public boolean test(String email) {
        return VALID_EMAIL_ADDRESS_REGEX
                .matcher(email)
                .find();
    }
}
