package com.barmjz.productivityapp.pomodoro;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class PomodoroSession extends Pomodoro {
    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean isStudying;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isPaused;
    @Column(nullable = false)
    private long startTime;
    @Column(nullable = false)
    private long remainingTimeInSecs;
}
//Pomodoro:
//id    1   2   usrId   3   4   5   6

//Pomodoro:
//id         1   2   usrId
//sessionId  3   4   5   6  id