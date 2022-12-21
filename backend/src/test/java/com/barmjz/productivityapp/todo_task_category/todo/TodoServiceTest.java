package com.barmjz.productivityapp.todo_task_category.todo;

import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTaskRepo;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

    @Mock
    private UserRepo userRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private OneTimeTaskRepo oneTimeTaskRepo;

    @Mock
    private RepeatedTaskRepo repeatedTaskRepo;

    @InjectMocks
    private TodoService todoService;

    @Test
    void addTodoTask() {
    }

    @Test
    void removeTaskFromTodo() {
    }
}