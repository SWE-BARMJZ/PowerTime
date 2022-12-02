package com.barmjz.productivityapp.controllers;

import com.barmjz.productivityapp.user.jwttoken.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
public class AuthController {

    private final JwtTokenService tokenService;


    @PostMapping("/token")
    public String token(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return token;
    }
}
