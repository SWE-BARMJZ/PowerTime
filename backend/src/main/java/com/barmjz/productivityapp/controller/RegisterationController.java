package com.barmjz.productivityapp.controller;

import com.barmjz.productivityapp.registeration.RegisterationRequest;
import com.barmjz.productivityapp.registeration.RegisterationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RegisterationController {

    private final RegisterationService registerationService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterationRequest registerationRequest){
        return registerationService.create(registerationRequest);
    }
}
