package com.barmjz.productivityapp.user;

import lombok.AllArgsConstructor;
import com.barmjz.productivityapp.user.registration.RegistrationRequest;
import com.barmjz.productivityapp.user.registration.RegistrationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }

    @GetMapping
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email);
    }

}
