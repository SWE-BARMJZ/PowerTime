package com.barmjz.productivityapp.todo_task_category.todo;

import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTaskRepo;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.todo_task_category.task.Task;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

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
        // given
        Long id = 124L;
        doNothing().when(oneTimeTaskRepo).changeTodoFlag(id, true);
        given(oneTimeTaskRepo.findById(id)).willReturn(Optional.of(new OneTimeTask()));

        // when
        Task task = todoService.addTodoTask(id);

        // then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).changeTodoFlag(argumentCaptor.capture(), isA(boolean.class));
        assertThat(argumentCaptor.getValue()).isEqualTo(id);
        verify(oneTimeTaskRepo, times(1)).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(id);
        assertThat(task).isNotNull();
    }

    @Test
    void removeTaskFromTodo() {
        // given
        long id = 1993L;
        String taskType = "onetime";
        doNothing().when(oneTimeTaskRepo).changeTodoFlag(id, false);
        given(oneTimeTaskRepo.findById(id)).willReturn(Optional.of(new OneTimeTask()));

        // when
        Task task = todoService.removeTaskFromTodo(id, taskType);

        // then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).changeTodoFlag(argumentCaptor.capture(), isA(boolean.class));
        assertThat(argumentCaptor.getValue()).isEqualTo(id);
        verify(oneTimeTaskRepo, times(1)).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(id);
        assertThat(task).isNotNull();
    }
}