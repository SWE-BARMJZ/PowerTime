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

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals(email))
            return userService.getUserByEmail(email);
        else
            throw new IllegalAccessException("Non authorized");
    }

}
