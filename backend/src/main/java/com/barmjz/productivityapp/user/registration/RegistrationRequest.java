package com.barmjz.productivityapp.user.registration;

public record
    RegistrationRequest(
        String email,
        String password,
        String firstName,
        String lastName
    ) {}
