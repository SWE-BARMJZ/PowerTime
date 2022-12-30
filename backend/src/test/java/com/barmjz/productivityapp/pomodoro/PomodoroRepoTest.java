package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PomodoroRepoTest {
    @Autowired
    private UserRepo userRepo;
    private User user;
    @Autowired
    private PomodoroRepo underTest;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("user1@gmail.com")
                .password("pass1")
                .firstName("user1First")
                .lastName("user1Last")
                .build();
        userRepo.save(user);
    }

    @Test
    void checkWhenPomodoroExists(){
        //given
        Pomodoro p = Pomodoro
                .builder()
                .studyTime(25)
                .breakTime(5)
                .user(user)
                .build();
        underTest.save(p);

        //when
        boolean exists = underTest.existsPomodoroByUserId(user.getId());

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void checkWhenPomodoroNotExist(){
        //when
        boolean exists = underTest.existsPomodoroByUserId(user.getId());

        //then
        assertThat(exists).isFalse();
    }

    @Test
    void checkWhenPomodoroSessionExists(){
        //given
        Pomodoro p = PomodoroSession
                .builder()
                .studyTime(25)
                .breakTime(5)
                .user(user)
                .isStudying(true)
                .build();
        underTest.save(p);

        //when
        p = underTest.getPomodoroByUserId(user.getId());

        //then
        assertThat(p).isInstanceOf(PomodoroSession.class);
    }



    @Test
    void checkWhenPomodoroSessionNotExists(){
        //given
        Pomodoro p = Pomodoro
                .builder()
                .studyTime(25)
                .breakTime(5)
                .user(user)
                .build();
        underTest.save(p);

        //when
        p = underTest.getPomodoroByUserId(user.getId());

        //then
        assertThat(p).isNotInstanceOf(PomodoroSession.class);
    }

}
