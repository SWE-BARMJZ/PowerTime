package com.barmjz.productivityapp.pomodoro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PomodoroRepo extends JpaRepository<Pomodoro,Long> {
    boolean existsPomodoroByUserId(Long userId);
    Pomodoro getPomodoroByUserId(Long userId);
}
