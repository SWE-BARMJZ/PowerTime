package com.barmjz.productivityapp.todo_task_category.category;

import com.barmjz.productivityapp.todo_task_category.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    long id;
    String category_name;
    List<Task> tasks;
}
