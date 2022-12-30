package com.barmjz.productivityapp.Pomodoro;

import com.barmjz.productivityapp.pomodoro.Pomodoro;
import com.barmjz.productivityapp.pomodoro.PomodoroRepo;
import com.barmjz.productivityapp.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserPomoDB {
    @Autowired UserRepo userRepo;
    @Autowired PomodoroRepo pomodoroRepo;
    User user1;
    Pomodoro pomodoro1;

    @BeforeEach
    void setUp(){
        user1 = User.builder()
                .email("user1@gmail.com")
                .password("pass1")
                .firstName("user1First")
                .lastName("user1Last")
                .build();
        userRepo.save(user1);
        pomodoro1 = Pomodoro.builder()
                .user(user1)
                .breakTime(5)
                .studyTime(25)
                .build();
        pomodoroRepo.save(pomodoro1);
    }

    @Test
    void checkUserExist(){
        assertThat(pomodoroRepo.existsPomodoroByUserId(user1.getId())).isTrue();
    }


}
