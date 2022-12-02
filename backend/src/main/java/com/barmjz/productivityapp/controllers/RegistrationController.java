package com.barmjz.productivityapp.controllers;

import com.barmjz.productivityapp.registration.RegistrationRequest;
import com.barmjz.productivityapp.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
}
