package com.barmjz.productivityapp.todo_task_category.task;

import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CategorisedTasks {
    CategoryDTO category;
    List<Task> tasks;

    public CategorisedTasks(Category category) {
        this.category = new CategoryDTO(category.getId(), category.getCategory_name());
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
