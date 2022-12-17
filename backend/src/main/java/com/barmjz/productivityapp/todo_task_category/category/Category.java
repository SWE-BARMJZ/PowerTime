package com.barmjz.productivityapp.todo_mindmap.category;

import com.barmjz.productivityapp.todo_mindmap.task.OneTimeTask;
import com.barmjz.productivityapp.todo_mindmap.task.RepeatedTask;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<OneTimeTask> oneTimeTasks;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<RepeatedTask> repeatedTasks;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
