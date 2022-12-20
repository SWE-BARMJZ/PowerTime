package com.barmjz.productivityapp.todo_task_category.task;

import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.UserRepo;
import org.h2.expression.OperationN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    Authentication userAuthentication;
    @Mock
    UserRepo userRepo;

    @Mock
    CategoryRepo categoryRepo;

    @Mock
    OneTimeTaskRepo oneTimeTaskRepo;

    @Mock
    RepeatedTaskRepo repeatedTaskRepo;

    @InjectMocks
    TaskService taskService;


//    @BeforeEach
//    void setUp() {
//        taskService = new TaskService(userRepo, categoryRepo, oneTimeTaskRepo, repeatedTaskRepo);
//    }

    @Test
    void getOneTimeTaskById() {
        // given
        Long taskId = 12L;
        Optional<OneTimeTask> optionalObject = Optional.of(new OneTimeTask());
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(true);
        given(oneTimeTaskRepo.findById(taskId)).willReturn(optionalObject);

        // when
        Task task = taskService.getTask(taskId);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo).existsById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo).findById(taskId);
        verify(repeatedTaskRepo, never()).findById(any());
        assertThat(task).isNotNull();
    }

    @Test
    void getRepeatedTaskById() {
        // given
        Long taskId = 12L;
        Optional<RepeatedTask> optionalObject = Optional.of(new RepeatedTask());
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(false);
        given(repeatedTaskRepo.findById(taskId)).willReturn(optionalObject);

        // when
        Task task = taskService.getTask(taskId);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo).existsById(taskId);
        verify(oneTimeTaskRepo, never()).findById(any());
        verify(repeatedTaskRepo).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        assertThat(task).isNotNull();
    }

    @Test
    void updateTask() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void createTask() {
    }

    @Test
    void tickTask() {
    }

    @Test
    void untickTask() {
    }

    @Test
    void getCompletedTask() {
    }
}