package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
public class Pomodoro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @Column(nullable = false)
    int studyTime;
    @Column(nullable = false)
    int breakTime;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    User user;

    Pomodoro copy() {
        return Pomodoro
                .builder()
                .studyTime(studyTime)
                .breakTime(breakTime)
                .user(user)
                .id(id)
                .build();
    }
}
