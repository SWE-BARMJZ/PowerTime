package com.barmjz.productivityapp.todo_mindmap.task;

import com.barmjz.productivityapp.todo_mindmap.category.CategoryRepo;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    


}
