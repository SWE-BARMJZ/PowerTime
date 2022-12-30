package com.barmjz.productivityapp.pomodoro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PomodoroRepo extends JpaRepository<Pomodoro,Long> {

}
