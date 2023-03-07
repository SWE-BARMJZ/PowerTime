package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@MappedSuperclass
public class Task extends AbstractTask {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;

    @Column(nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnore
    private User user;

}