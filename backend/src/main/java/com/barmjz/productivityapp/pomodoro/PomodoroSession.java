package com.barmjz.productivityapp.pomodoro;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
public class PomodoroSession extends Pomodoro {
    @ColumnDefault("true")
    private boolean isStudying;
    @ColumnDefault("false")
    private boolean isPaused;
    private long startTime;
    private long remainingTimeInSecs;

    Pomodoro copy() {
        return PomodoroSession
                .builder()
                .studyTime(studyTime)
                .breakTime(breakTime)
                .user(user)
                .id(id)
                .isStudying(isStudying)
                .isPaused(isPaused)
                .remainingTimeInSecs(remainingTimeInSecs)
                .startTime(startTime)
                .build();
    }

    Pomodoro extractPomo(){
        return Pomodoro
                .builder()
                .studyTime(studyTime)
                .breakTime(breakTime)
                .user(user)
                .id(id)
                .build();
    }
}
