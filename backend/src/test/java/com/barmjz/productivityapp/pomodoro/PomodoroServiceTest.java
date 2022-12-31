package com.barmjz.productivityapp.pomodoro;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PomodoroServiceTest {
    @Autowired
    UserRepo userRepo;

    @Mock
    PomodoroRepo pomoRepo;

    User user;
    PomodoroService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PomodoroService(pomoRepo);
        user = User.builder()
                .email("user1@gmail.com")
                .password("pass1")
                .firstName("user1First")
                .lastName("user1Last")
                .build();
        userRepo.save(user);
    }

    //Testing get
    @Test
    void checkPomodoroRetrievedWhenFirstTime(){
        //given
        Pomodoro firstPomo = Pomodoro.builder().id(1L).studyTime(25).breakTime(5).user(user).build();

        //when
        underTest.get(user);

        //then
        ArgumentCaptor<Pomodoro> pomodoroArgumentCaptor = ArgumentCaptor.forClass(Pomodoro.class);
        verify(pomoRepo).save(pomodoroArgumentCaptor.capture());
        assertThat(pomodoroArgumentCaptor.getValue()).isEqualTo(firstPomo);
    }

    @Test
    void checkPomodoroRetrievedWhenNthTime(){
        //given
        Pomodoro p = Pomodoro.builder().id(1L).studyTime(25).breakTime(5).user(user).build();
        given(pomoRepo.existsPomodoroByUserId(user.getId())).willReturn(true);
        given(pomoRepo.getPomodoroByUserId(user.getId())).willReturn(p);

        //when
        Pomodoro returnedP = underTest.get(user);

        //then
        assertThat(returnedP).isEqualTo(p);
    }

    @Test
    void checkPSessionRetrievedWhenPaused(){
        //given
        PomodoroSession p = PomodoroSession
                .builder()
                .id(1L)
                .studyTime(25)
                .breakTime(5)
                .isPaused(true)
                .user(user).build();
        given(pomoRepo.existsPomodoroByUserId(user.getId())).willReturn(true);
        given(pomoRepo.getPomodoroByUserId(user.getId())).willReturn(p);

        //when
        Pomodoro returnedP = underTest.get(user);

        //then
        assertThat(returnedP).isEqualTo(p);
    }

    @Test
    void checkPSessionRunningInBackground(){
        //given
        PomodoroSession p = PomodoroSession
                .builder()
                .id(1L)
                .studyTime(25)
                .breakTime(5)
                .isPaused(false)
                .isStudying(true)
                .startTime(System.currentTimeMillis()/1000 - 20*60L)
                .remainingTimeInSecs(25*60L)
                .user(user).build();
        given(pomoRepo.existsPomodoroByUserId(user.getId())).willReturn(true);
        given(pomoRepo.getPomodoroByUserId(user.getId())).willReturn(p);

        //when
        PomodoroSession returnedP = (PomodoroSession) underTest.get(user);

        //then
        p.setRemainingTimeInSecs(5*60L);
        assertThat(returnedP).isEqualTo(p);
    }

}
