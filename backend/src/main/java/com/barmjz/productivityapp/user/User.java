package com.barmjz.productivityapp.user;

import com.barmjz.productivityapp.todomindmap.task.OneTimeTask;
import com.barmjz.productivityapp.todomindmap.task.RepeatedTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table( name = "`user`",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique_const",
                columnNames = "email"
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String imageURL;

    private Boolean emailVerified;

    @OneToMany(mappedBy = "user")
    private Set<OneTimeTask> oneTimeTasks;

    @OneToMany(mappedBy = "user")
    private Set<RepeatedTask> repeatedTasks;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailVerified = false;
    }

}
