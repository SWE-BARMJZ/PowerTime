package com.barmjz.productivityapp.todo_task_category.todo;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTask;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTask;
import com.barmjz.productivityapp.todo_task_category.task.Task;
import com.barmjz.productivityapp.todo_task_category.task.OneTimeTaskRepo;
import com.barmjz.productivityapp.todo_task_category.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class TodoService {

    private final UserRepo userRepo;

    private final OneTimeTaskRepo oneTimeTaskRepo;

    private final RepeatedTaskRepo repeatedTaskRepo;


    public List<Task> getTasks(long date){
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(getRepeatedTasks(date));
        tasks.addAll(getOnetimeTasks());
        return tasks;
    }

    private List<OneTimeTask> getOnetimeTasks(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByEmail(auth.getName()).orElseThrow();
        return oneTimeTaskRepo.getAllByUserIdAndTodoEqualsAndCompletionDateIsNullOrderByDueDate(user.getId(), true).orElseThrow();
    }

    private List<RepeatedTask> getRepeatedTasks(long date){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByEmail(auth.getName()).orElseThrow();
        Date currentDate = new Date(date);
        String currentDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(currentDate.getTime());
        return switch (currentDay) {
            case "Saturday" ->
                    repeatedTaskRepo.getAllByUserAndSaturdayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Sunday" ->
                    repeatedTaskRepo.getAllByUserAndSundayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Monday" ->
                    repeatedTaskRepo.getAllByUserAndMondayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Tuesday" ->
                    repeatedTaskRepo.getAllByUserAndTuesdayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Wednesday" ->
                    repeatedTaskRepo.getAllByUserAndWednesdayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Thursday" ->
                    repeatedTaskRepo.getAllByUserAndThursdayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            case "Friday" ->
                    repeatedTaskRepo.getAllByUserAndFridayEqualsAndLastRemovalDateNot(user, true, currentDate).orElse(null);
            default -> null;
        };
    }


    public Task addTodoTask(long id){
        oneTimeTaskRepo.changeTodoFlag(id, true);
        return oneTimeTaskRepo.findById(id).get();
    }

    public void removeTaskFromTodo(long id, String taskType){
        if (taskType.equals("onetime"))
        {
            oneTimeTaskRepo.changeTodoFlag(id, false);
        }
        else{
            repeatedTaskRepo.changeRemovalDate(id, new Date());
        }
    }


}
