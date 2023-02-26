package com.barmjz.productivityapp.todo_task_category.category;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTask;
import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(name = "categoryUserKey", columnNames = {"category_name", "user_id"}))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String category_name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OneTimeTask> oneTimeTasks;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RepeatedTask> repeatedTasks;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Category(String category_name, User user) {
        this.category_name = category_name;
        this.user = user;
    }

}
