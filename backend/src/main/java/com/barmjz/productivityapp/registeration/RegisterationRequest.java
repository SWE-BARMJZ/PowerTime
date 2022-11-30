package com.barmjz.productivityapp.registeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterationRequest {
    private final String email;
    private final String password;
}
