package com.barmjz.productivityapp.task;
import com.barmjz.productivityapp.category.Category;
import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnore
    private User user;

}