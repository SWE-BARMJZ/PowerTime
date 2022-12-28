package com.barmjz.productivityapp.pomodoro;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PomodoroSession extends Pomodoro {
    private boolean isStudying = true;
    private boolean isPaused = false;
    private long startTime;
    private long remainingTimeInSecs=studyTime * 60L;
}
