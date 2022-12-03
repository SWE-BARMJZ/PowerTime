package com.barmjz.productivityapp.user;

import lombok.AllArgsConstructor;
import com.barmjz.productivityapp.user.registration.RegistrationRequest;
import com.barmjz.productivityapp.user.registration.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }

}
