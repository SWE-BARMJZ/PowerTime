package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PomodoroRepo extends JpaRepository<Pomodoro,Long> {
    //should be findPomodoroByUserId but not working
//    Pomodoro findPomodoroByUser(Long userId);
//    boolean existsByUser(User user);
}
