package com.barmjz.productivityapp.mind_map.model;
import com.barmjz.productivityapp.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Task extends AbstractTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date creationDate;

    @ManyToOne()
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

}
