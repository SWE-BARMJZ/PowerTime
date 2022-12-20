package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
        if (oneTimeTaskRepo.existsById(taskId))
            return oneTimeTaskRepo.findById(taskId).orElse(null);
        return repeatedTaskRepo.findById(taskId).orElse(null);
    }

    public Task updateTask(Long taskId, Task task){
        Task updatedTask;
        if (oneTimeTaskRepo.existsById(taskId)) {
            OneTimeTask oneTimeTask = (OneTimeTask) task;
            oneTimeTaskRepo.save(oneTimeTask);
            updatedTask = oneTimeTaskRepo.findById(taskId).orElse(null);
        }
        else {
            RepeatedTask repeatedTask = (RepeatedTask) task;
            repeatedTaskRepo.save(repeatedTask);
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
            newTask = oneTimeTaskRepo.getByCreationDate(task.getCreationDate()).orElse(null);
        } else {
            repeatedTaskRepo.save((RepeatedTask) task);
            newTask = repeatedTaskRepo.getByCreationDate(task.getCreationDate()).orElse(null);
        }
        return newTask;
    }

    public Task tickTask(Long taskId, Long date, String taskType){
        Task newTask;
        Date currentDate = new Date(date);
        User user = userRepo.getUserByEmail(userAuthentication.getName()).orElse(null);
        if (taskType.equals("onetime")) {
            oneTimeTaskRepo.markTaskAsDone(taskId, currentDate);
            newTask = oneTimeTaskRepo.findById(taskId).get();
        } else {
            RepeatedTask repeatedTask = repeatedTaskRepo.findById(taskId).orElse(null);
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
            newTask = oneTimeTaskRepo.getByCreationDate(repeatedTask.getCreationDate()).orElse(null);
        }
        return newTask;
    }

    public Task untickTask(Long taskId, Long date){
        Task newTask;
        Date currentDate = new Date(date);
        Instant time = currentDate.toInstant();
        Date yesterday = Date.from(time.minus(1, ChronoUnit.DAYS));
        OneTimeTask oneTimeTask = oneTimeTaskRepo.findById(taskId).orElse(null);
        assert oneTimeTask != null;
        if (repeatedTaskRepo.existsByCreationDate(oneTimeTask.getCreationDate())){
            RepeatedTask repeatedTask = repeatedTaskRepo.getByCreationDate(oneTimeTask.getCreationDate()).orElse(null);
            assert repeatedTask != null;
            repeatedTaskRepo.changeRemovalDate(repeatedTask.getId(), yesterday);
            newTask = repeatedTaskRepo.findById(repeatedTask.getId()).orElse(null);
            oneTimeTaskRepo.deleteById(taskId);
        }
        else {
            oneTimeTaskRepo.unMarkTaskAsDone(taskId);
            newTask = oneTimeTaskRepo.findById(taskId).orElse(null);
        }
        return newTask;
    }

    public List<Task> getCompletedTasks(){
        User user = userRepo.getUserByEmail(userAuthentication.getName()).orElse(null);
        List<OneTimeTask> completedOneTimeTasks =  oneTimeTaskRepo.getCompletedTasks(user).orElse(null);
        assert completedOneTimeTasks != null;
        return new ArrayList<>(completedOneTimeTasks);
    }


    public List<CategoryPair> getCategorizedTasks(){
        String user = userAuthentication.getName();
        List<CategoryPair> categoryTaskPairs = new ArrayList<>();
        List<Category> categories = categoryRepo
                .getCategoryByUserId(
                        userRepo.getUserByEmail(user)
                                .get()
                                .getId())
                .get();
        List<Task> categoryTasks = new ArrayList<>();
        for (Category category: categories){
            categoryTasks.addAll(oneTimeTaskRepo
                    .getAllByCategory(category)
                    .get());
            categoryTasks.addAll(repeatedTaskRepo
                    .getAllByCategory(category)
                    .get());
            categoryTaskPairs.add(new CategoryPair(category, categoryTasks));
        }
        return categoryTaskPairs;
    }

}
