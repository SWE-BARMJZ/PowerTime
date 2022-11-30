package com.barmjz.productivityapp.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique_const",
                columnNames = "email"
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
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

    private String roles;


    public User(String email, String password, String firstName, String lastName, String roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

}
