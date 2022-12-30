package com.barmjz.productivityapp.Folder;

import com.barmjz.productivityapp.Note.Note;
import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Date CreatedDate;
    private Date modifiedDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Note> notes;
}

