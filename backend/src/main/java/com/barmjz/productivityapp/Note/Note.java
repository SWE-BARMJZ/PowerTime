package com.barmjz.productivityapp.Note;

import com.barmjz.productivityapp.Folder.Folder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(
        name = "note"
//        ,uniqueConstraints = @UniqueConstraint(
//                name = "note_unique_const",
//                columnNames = {"title","folder_id"}
//        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    /*
        one to many relation
        one folder -> many notes
        one note -> one folder
        there are built in folders(starred, trashed,...)
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
    private String title;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
    private boolean isStarred;
    private int fontSize;
    private int color;

    @ManyToOne(
            optional = false
            ,cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "folder_id",
            referencedColumnName = "id"
    )
    private Folder folder;
}
