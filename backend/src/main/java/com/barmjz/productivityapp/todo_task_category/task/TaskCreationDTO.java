package com.barmjz.productivityapp.todo_task_category.task;

import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskCreationDTO {
    private String name;
    private String description;
    private CategoryDTO category;

    // one time
    private Date dueDate;
    // repeated
    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
}
