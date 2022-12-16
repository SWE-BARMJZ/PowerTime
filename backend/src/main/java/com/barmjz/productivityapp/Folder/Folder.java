package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(
        name = "folder",
        uniqueConstraints = @UniqueConstraint(
                name = "folder_unique_const",
                columnNames = {"name","user_id"}
        )

)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Folder {
    /*
        one to many relation
        one folder -> one user
        one user -> many folder
    */
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
    private String name;

    private LocalDate CreatedDate;
    private LocalDate modifiedDate;

    @ManyToOne(
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;
}
