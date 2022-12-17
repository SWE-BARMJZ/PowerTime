package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

    private final UserRepo userRepo;

    private final CategoryRepo categoryRepo;

    private final OneTimeTaskRepo oneTimeTaskRepo;

    private final RepeatedTaskRepo repeatedTaskRepo;

    public Task getTask(Long taskId){
        Task fetchedTask;
        if (oneTimeTaskRepo.existsById(taskId))
            fetchedTask = oneTimeTaskRepo.findById(taskId).get();
        else
            fetchedTask = repeatedTaskRepo.findById(taskId).get();
        return fetchedTask;
    }

    public Task updateTask(Long taskId, Task task){
        Task updatedTask;
        if (oneTimeTaskRepo.existsById(taskId)) {
            oneTimeTaskRepo.deleteById(taskId);
            OneTimeTask oneTimeTask = (OneTimeTask) task;
            oneTimeTaskRepo.save(oneTimeTask);
            updatedTask = oneTimeTaskRepo.findById(taskId).get();
        }
        else{
            repeatedTaskRepo.deleteById(taskId);
            RepeatedTask oneTimeTask = (RepeatedTask) task;
            repeatedTaskRepo.save(oneTimeTask);
            updatedTask = repeatedTaskRepo.findById(taskId).get();
        }
        return updatedTask;
    }

    public void deleteTask(Long taskId){
        if (oneTimeTaskRepo.existsById(taskId))
            oneTimeTaskRepo.deleteById(taskId);
        else
            repeatedTaskRepo.deleteById(taskId);
    }

    public Task createTask(Task task, String taskType){
        Task newTask;
        if (taskType.equals("onetime")) {
            oneTimeTaskRepo.save((OneTimeTask) task);
            newTask = oneTimeTaskRepo.getByCreationDate(task.getCreationDate()).get();
        }else {
            repeatedTaskRepo.save((RepeatedTask) task);
            newTask = repeatedTaskRepo.getByCreationDate(task.getCreationDate()).get();
        }
        return newTask;
    }

    public Task tickTask(Long taskId, Long date, String taskType){
        Task newTask;
        Date currentDate = new Date(date);
        User user = userRepo.getUserByEmail(userAuthentication.getName()).get();
        if (taskType.equals("onetime")) {
            oneTimeTaskRepo.markTaskAsDone(taskId, currentDate);
            newTask = oneTimeTaskRepo.findById(taskId).get();
        }else {
            RepeatedTask repeatedTask = repeatedTaskRepo.findById(taskId).get();
            OneTimeTask parsedRepeatedTask = OneTimeTask.builder()
                    .taskName(repeatedTask.getTaskName())
                    .creationDate(repeatedTask.getCreationDate())
                    .user(user)
                    .taskDesc(repeatedTask.getTaskDesc())
                    .todo(false)
                    .completionDate(currentDate)
                    .build();
            oneTimeTaskRepo.save(parsedRepeatedTask);
            repeatedTaskRepo.changeRemovalDate(taskId, currentDate);
            newTask = repeatedTaskRepo.findById(taskId).get();
        }
        return newTask;
    }

    public Task untickTask(Long taskId, Long date){
        Task newTask;
        Date currentDate = new Date(date);
        Instant time = currentDate.toInstant();
        Date yesterday = (Date) Date.from(time.minus(1, ChronoUnit.DAYS));
        OneTimeTask oneTimeTask = oneTimeTaskRepo.findById(taskId).get();
        if (repeatedTaskRepo.existsByCreationDate(oneTimeTask.getCreationDate())){
            RepeatedTask repeatedTask = repeatedTaskRepo.getByCreationDate(oneTimeTask.getCreationDate()).get();
            repeatedTaskRepo.changeRemovalDate(repeatedTask.getId(), yesterday);
            newTask = repeatedTaskRepo.findById(repeatedTask.getId()).get();
            oneTimeTaskRepo.deleteById(taskId);
        }
        else {
            oneTimeTaskRepo.unMarkTaskAsDone(taskId);
            newTask = oneTimeTaskRepo.findById(taskId).get();
        }
        return newTask;
    }

    public List<Task> getCompletedTask(){
        User user = userRepo.getUserByEmail(userAuthentication.getName()).get();
        List<OneTimeTask> completedOneTimeTasks =  oneTimeTaskRepo.getCompletedTasks(user).get();
        List<Task> completedTasks = new ArrayList<>();
        completedTasks.addAll(completedOneTimeTasks);
        return completedTasks;
    }

}
