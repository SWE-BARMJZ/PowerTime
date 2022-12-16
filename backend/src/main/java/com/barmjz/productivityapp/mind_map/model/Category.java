package com.barmjz.productivityapp.mind_map.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    private List<OneTimeTask> oneTimeTasks;

    @OneToMany(mappedBy = "category")
    private List<RepeatedTask> repeatedTasks;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
