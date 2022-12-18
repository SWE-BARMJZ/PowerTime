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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@AllArgsConstructor
public class TodoService {
    private final Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

    private final UserRepo userRepo;

    private final CategoryRepo categoryRepo;

    private final OneTimeTaskRepo oneTimeTaskRepo;

    private final RepeatedTaskRepo repeatedTaskRepo;

    public List<Task> getTasks(long date){
        List<Task> tasks = new ArrayList<>();
        List<RepeatedTask> repeatedTasks = getRepeatedTasks(date);
        List<OneTimeTask> oneTimeTasks = getOnetimeTasks();
        tasks.addAll(repeatedTasks);
        tasks.addAll(oneTimeTasks);
        return tasks;
    }
    private List<OneTimeTask> getOnetimeTasks(){
        User user = userRepo.getUserByEmail(userAuthentication.getName()).get();
        return oneTimeTaskRepo.getAllByUserIdAndTodoEqualsOrderByDueDate(user.getId(), true).orElseThrow();
    }

    private List<RepeatedTask> getRepeatedTasks(long date){
        User user = userRepo.getUserByEmail(userAuthentication.getName()).get();
        Date currentDate = new Date(date);
        String currentDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(currentDate.getTime());
        Optional<List<RepeatedTask>> repeatedTasks;
        switch (currentDay){
            case "Saturday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndSaturdayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Sunday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndSundayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Monday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndMondayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Tuesday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndTuesdayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Wednesday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndWednesdayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Thursday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndThursdayEqualsAndLastRemovalDateNot(user, true, currentDate);
            case "Friday":
                repeatedTasks = repeatedTaskRepo.getAllByUserAndFridayEqualsAndLastRemovalDateNot(user, true, currentDate);
            default:
                repeatedTasks = null;
        }
        return repeatedTasks.get();
    }

//    public void addOnetimeTask(OneTimeTask oneTimeTask){
//        oneTimeTaskRepo.save(oneTimeTask);
//    }
//
//    public void addRepeatedTask(RepeatedTask repeatedTask){
//        repeatedTaskRepo.save(repeatedTask);
//    }

    public void addTodoTask(long id){
        oneTimeTaskRepo.changeTodoFlagToTrue(id);
    }


    public void createNewTodoTask(long id){
        oneTimeTaskRepo.changeTodoFlagToTrue(id);
    }


    public void markOnetimeTask(Long id){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        oneTimeTaskRepo.markTaskAsDone(id, (java.sql.Date) date);

    }

    public void unmarkOneTimeTask(Long id){
        oneTimeTaskRepo.unMarkTaskAsDone(id);
    }

    public void markRepeatedTask(Long id){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        repeatedTaskRepo.changeRemovalDate(id, date);
    }

    public void unMarkRepeatedTask(long id){
        RepeatedTask unmakredTask = repeatedTaskRepo.getById(id);
        Instant now = unmakredTask.getLastRemovalDate().toInstant();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        repeatedTaskRepo.changeRemovalDate(id, Date.from(yesterday));
    }

//    public Map<String, List<OneTimeTask>> categorizeOnetimeTasks(){
//        Map<String, List<OneTimeTask>> stringListMap = new HashMap<>();
//        List<Category> categories = categoryRepo.findAll();
//        for (Category category: categories){
//            List<OneTimeTask> categoryTasks = oneTimeTaskRepo.getAllByCategory(category).get();
//            stringListMap.put(category.getCategoryName(), categoryTasks);
//        }
//        return stringListMap;
//    }
//
//    public Map<String, List<RepeatedTask>> categorizeRepeatedTasks(){
//        Map<String, List<RepeatedTask>> stringListMap = new HashMap<>();
//        List<Category> categories = categoryRepo.findAll();
//        for (Category category: categories){
//            List<RepeatedTask> categoryTasks = repeatedTaskRepo.getAllByCategory(category).get();
//            stringListMap.put(category.getCategoryName(), categoryTasks);
//        }
//        return stringListMap;
//    }


}