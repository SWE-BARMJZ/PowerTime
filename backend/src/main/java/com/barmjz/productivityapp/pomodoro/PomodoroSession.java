package com.barmjz.productivityapp.pomodoro;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PomodoroSession extends Pomodoro {
    private boolean isStudying = true;
    private boolean isPaused = false;
    private long startTime;
    private long remainingTimeInSecs=studyTime * 60L;
}
