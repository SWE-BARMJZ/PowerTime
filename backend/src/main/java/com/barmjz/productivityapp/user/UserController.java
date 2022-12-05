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

    @GetMapping("/test")
    public String register(@RequestParam String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return (authentication.getName().equals(email))? userService.loadUserByUsername(email).getUsername(): "you stoopid";
    }

}
