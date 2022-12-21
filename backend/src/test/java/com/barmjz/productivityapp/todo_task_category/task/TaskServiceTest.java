package com.barmjz.productivityapp.todo_task_category.task;

import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
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
        verify(oneTimeTaskRepo, times(1)).existsById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo, times(1)).findById(taskId);
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
        verify(oneTimeTaskRepo, times(1)).existsById(taskId);
        verify(oneTimeTaskRepo, never()).findById(any());
        verify(repeatedTaskRepo, times(1)).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        assertThat(task).isNotNull();
    }

    @Test
    void updateTaskGivenItIsOneTimeTask() {
        // given
        Long taskId = 10L;
        User user = new User("basel20ahmed@gmail.com", "Abbdjhd_1099", "Basel", "Ahmed");
        Category category = new Category("Projects", user);
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Milestone 2 SWE")
                .dueDate(Date.valueOf("2022-12-21"))
                .user(user)
                .category(category)
                .build();

        Optional<OneTimeTask> optionalObject = Optional.of(new OneTimeTask());
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(true);
        given(oneTimeTaskRepo.save(task)).willReturn(any());
        given(oneTimeTaskRepo.findById(taskId)).willReturn(optionalObject);

        // when
        Task updatedTask = taskService.updateTask(taskId, task);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).existsById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        ArgumentCaptor<OneTimeTask> taskArgumentCaptor = ArgumentCaptor.forClass(OneTimeTask.class);
        verify(oneTimeTaskRepo, times(1)).save(taskArgumentCaptor.capture());
        assertThat(taskArgumentCaptor.getValue()).isEqualTo(task);
        verify(oneTimeTaskRepo, times(1)).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);

        verify(repeatedTaskRepo, never()).save(any());
        verify(repeatedTaskRepo, never()).findById(any());
        assertThat(updatedTask).isNotNull();
    }

    @Test
    void updateTaskGivenItIsRepeatedTask() {
        // given
        Long taskId = 11L;
        User user = new User("basel20ahmed@gmail.com", "Abbdjhd_1099", "Basel", "Ahmed");
        Category category = new Category("Sports", user);
        RepeatedTask task = RepeatedTask.builder()
                .taskName("Gym")
                .sunday(true)
                .thursday(true)
                .user(user)
                .category(category)
                .build();

        Optional<RepeatedTask> optionalObject = Optional.of(new RepeatedTask());
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(false);
        given(repeatedTaskRepo.save(task)).willReturn(any());
        given(repeatedTaskRepo.findById(taskId)).willReturn(optionalObject);

        // when
        Task updatedTask = taskService.updateTask(taskId, task);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).existsById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo, never()).save(any());
        verify(oneTimeTaskRepo, never()).findById(any());
        ArgumentCaptor<RepeatedTask> taskArgumentCaptor = ArgumentCaptor.forClass(RepeatedTask.class);
        verify(repeatedTaskRepo, times(1)).save(taskArgumentCaptor.capture());
        assertThat(taskArgumentCaptor.getValue()).isEqualTo(task);
        verify(repeatedTaskRepo, times(1)).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        assertThat(updatedTask).isNotNull();
    }

    @Test
    void deleteOneTimeTask() {
        // given
        Long taskId = 28L;
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(true);
        doNothing().when(oneTimeTaskRepo).deleteById(taskId);

        // when
        taskService.deleteTask(taskId);

        // then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).existsById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo, times(1)).deleteById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(taskId);
        verify(repeatedTaskRepo, never()).deleteById(any());
    }

    @Test
    void deleteRepeatedTask() {
        // given
        Long taskId = 29L;
        given(oneTimeTaskRepo.existsById(taskId)).willReturn(false);
        doNothing().when(repeatedTaskRepo).deleteById(taskId);

        // when
        taskService.deleteTask(taskId);

        // then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).existsById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo, never()).deleteById(any());
        verify(repeatedTaskRepo, times(1)).deleteById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(taskId);
    }

    @Test
    void createTask() {
        // given
        User user = new User("basel20ahmed@gmail.com", "Abbdjhd_1099", "Basel", "Ahmed");
        Category category = new Category("Projects", user);
        OneTimeTask task = OneTimeTask.builder()
                .taskName("Milestone 2 SWE")
                .dueDate(Date.valueOf("2022-12-21"))
                .user(user)
                .category(category)
                .build();

        String taskType = "onetime";
        given(oneTimeTaskRepo.save(task)).willReturn(any());
        Optional<OneTimeTask> optionalObject = Optional.of(new OneTimeTask());
        given(oneTimeTaskRepo.getByCreationDate(task.getCreationDate())).willReturn(optionalObject);

        // when
        taskService.createTask(task, "onetime");

        // then
        ArgumentCaptor<OneTimeTask> argumentCaptor = ArgumentCaptor.forClass(OneTimeTask.class);
        verify(oneTimeTaskRepo, times(1)).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(task);
        ArgumentCaptor<Date> dateArgumentCaptor = ArgumentCaptor.forClass(Date.class);
        verify(oneTimeTaskRepo, times(1)).getByCreationDate(dateArgumentCaptor.capture());
        assertThat(dateArgumentCaptor.getValue()).isEqualTo(task.getCreationDate());
        verify(repeatedTaskRepo, never()).save(any());
        verify(repeatedTaskRepo, never()).getByCreationDate(any());
    }

    @Test
    void untickRepeatedTask() {
        // given
        long taskId = 12L, date = 24488158484848L;
        java.util.Date currentDate = new java.util.Date(date);
        Instant time = currentDate.toInstant();
        java.util.Date yesterday = java.util.Date.from(time.minus(1, ChronoUnit.DAYS));
        Optional<OneTimeTask> optionalOneTimeTask = Optional.of(OneTimeTask.builder().creationDate(Date.valueOf("2022-01-01")).build());
        given(oneTimeTaskRepo.findById(taskId)).willReturn(optionalOneTimeTask);
        given(repeatedTaskRepo.existsByCreationDate(optionalOneTimeTask.get().getCreationDate())).willReturn(true);
        Optional<RepeatedTask> optionalRepeatedTask = Optional.of(RepeatedTask.builder().id(123L).build());
        given(repeatedTaskRepo.getByCreationDate(optionalOneTimeTask.get().getCreationDate())).willReturn(optionalRepeatedTask);
        doNothing().when(repeatedTaskRepo).changeRemovalDate(optionalRepeatedTask.get().getId(), yesterday);
        given(repeatedTaskRepo.findById(optionalRepeatedTask.get().getId())).willReturn(optionalRepeatedTask);

        // when
        Task newTask = taskService.untickTask(taskId, date);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(1)).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(repeatedTaskRepo, times(1)).existsByCreationDate(isA(java.util.Date.class));
        verify(repeatedTaskRepo, times(1)).changeRemovalDate(isA(Long.class), isA(java.util.Date.class));
        verify(repeatedTaskRepo, times(1)).findById(isA(Long.class));
        verify(oneTimeTaskRepo).deleteById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(oneTimeTaskRepo, never()).unMarkTaskAsDone(any());
        assertThat(newTask).isNotNull();
    }

    @Test
    void untickOneTimeTask() {
        // given
        long taskId = 12L, date = 24488158484848L;
        java.util.Date currentDate = new java.util.Date(date);
        Instant time = currentDate.toInstant();
        java.util.Date yesterday = java.util.Date.from(time.minus(1, ChronoUnit.DAYS));
        Optional<OneTimeTask> optionalOneTimeTask = Optional.of(OneTimeTask.builder().creationDate(Date.valueOf("2022-01-01")).build());
        given(oneTimeTaskRepo.findById(taskId)).willReturn(optionalOneTimeTask);
        given(repeatedTaskRepo.existsByCreationDate(optionalOneTimeTask.get().getCreationDate())).willReturn(false);
        doNothing().when(oneTimeTaskRepo).unMarkTaskAsDone(taskId);
        given(oneTimeTaskRepo.findById(taskId)).willReturn(optionalOneTimeTask);

        // when
        Task newTask = taskService.untickTask(taskId, date);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(oneTimeTaskRepo, times(2)).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        verify(repeatedTaskRepo, times(1)).existsByCreationDate(isA(java.util.Date.class));
        verify(repeatedTaskRepo, never()).changeRemovalDate(any(), any());
        verify(repeatedTaskRepo, never()).findById(any());
        verify(oneTimeTaskRepo, never()).deleteById(any());
        verify(oneTimeTaskRepo, times(1)).unMarkTaskAsDone(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(taskId);
        assertThat(newTask).isNotNull();
    }
}