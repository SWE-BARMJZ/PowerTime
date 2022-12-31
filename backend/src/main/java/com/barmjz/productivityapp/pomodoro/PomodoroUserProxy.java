package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PomodoroUserProxy {
    private final UserRepo userRepo;

    public Long getCurrentUserId(){
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.getUserByEmail(userAuthentication.getName()).get().getId();
    }

    public User getCurrentUser(){
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.getUserByEmail(userAuthentication.getName()).get();
    }

}
