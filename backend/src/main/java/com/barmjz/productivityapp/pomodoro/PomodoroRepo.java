package com.barmjz.productivityapp.pomodoro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PomodoroRepo extends JpaRepository<Pomodoro,Long> {
    public boolean existsPomodoroByUserId(Long userId);
    public Pomodoro getPomodoroByUserId(Long userId);
}
