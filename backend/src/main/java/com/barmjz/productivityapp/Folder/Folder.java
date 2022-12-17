package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(
        name = "folder"
//        ,uniqueConstraints = @UniqueConstraint(
//                name = "folder_unique_const",
//                columnNames = {"user_id","name"}
//        )

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
    private Date CreatedDate;
    private Date modifiedDate;

    @ManyToOne(
            optional = false,
            cascade = CascadeType.ALL
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private User user;
}

