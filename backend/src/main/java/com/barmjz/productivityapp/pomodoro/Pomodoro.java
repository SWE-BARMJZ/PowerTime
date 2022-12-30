package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Pomodoro {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 0
    )
    private Long id;

    @Column(nullable = false)
    int studyTime;
    @Column(nullable = false)
    int breakTime;

    @OneToOne
    @JoinColumn(
            name= "userId", referencedColumnName = "id"
    )
    @JsonIgnore
    private User user;
}