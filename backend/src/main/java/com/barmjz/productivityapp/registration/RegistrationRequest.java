package com.barmjz.productivityapp.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationRequest {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
}
