package com.barmjz.productivityapp.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService tokenService;

    @PostMapping("/token")
    public String authenticate(Authentication auth) {
        return tokenService.generateToken(auth);
    }

}
