package com.barmjz.productivityapp.todo_task_category.task;

import com.barmjz.productivityapp.todo_task_category.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryPair {
    long id;
    String name;
    List<Task> tasks;
}
