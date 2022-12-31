package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.todo_task_category.category.CategoryDTO;
import com.barmjz.productivityapp.todo_task_category.category.CategoryRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final UserRepo userRepo;

    private final CategoryRepo categoryRepo;

    private final OneTimeTaskRepo oneTimeTaskRepo;

    private final RepeatedTaskRepo repeatedTaskRepo;

    @Autowired
    public TaskService(UserRepo userRepo, CategoryRepo categoryRepo, OneTimeTaskRepo oneTimeTaskRepo, RepeatedTaskRepo repeatedTaskRepo) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.oneTimeTaskRepo = oneTimeTaskRepo;
        this.repeatedTaskRepo = repeatedTaskRepo;
    }

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

    public Long createOneTimeTask(TaskCreationDTO taskCreationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        OneTimeTask task = modelMapper.map(taskCreationDTO, OneTimeTask.class);
        initCreatedTaskFromDTO(task, taskCreationDTO);
        oneTimeTaskRepo.save(task);
        return task.getId();
    }

    public Long createRepeatedTask(TaskCreationDTO taskCreationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        RepeatedTask task = modelMapper.map(taskCreationDTO, RepeatedTask.class);
        initCreatedTaskFromDTO(task, taskCreationDTO);
        repeatedTaskRepo.save(task);
        return task.getId();
    }

    private void initCreatedTaskFromDTO(Task task, TaskCreationDTO taskCreationDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo
                .getUserByEmail(auth.getName()).orElseThrow();
        task.setUser(currentUser);

        Date now = new Date();
        task.setCreationDate(now);

        if (taskCreationDTO.getCategory() != null) {
            String categoryName = taskCreationDTO.getCategory().getCategory_name();
            Category category = categoryRepo
                    .getCategoryByCategoryNameAndUser(categoryName, currentUser)
                    .orElseThrow();
            task.setCategory(category);
        }
    }

    public Task tickTask(Long taskId, Long date, String taskType){
        Task newTask;
        Date currentDate = new Date(date);
        User user = userRepo.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
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
        User user = userRepo.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        List<OneTimeTask> completedOneTimeTasks = oneTimeTaskRepo.getCompletedTasks(user).orElse(null);
        assert completedOneTimeTasks != null;
        return new ArrayList<>(completedOneTimeTasks);
    }

    public List<Task> getAllTasks() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.getUserByEmail(userEmail).orElseThrow();
        Long userId = currentUser.getId();

        List<OneTimeTask> oneTimeTasks = oneTimeTaskRepo
                .getAllByUserId(userId)
                .orElseGet(ArrayList::new);
        List<RepeatedTask> repeatedTasks = repeatedTaskRepo
                .getAllByUserId(userId)
                .orElseGet(ArrayList::new);

        List<Task> tasks = new ArrayList<>();
        tasks.addAll(oneTimeTasks);
        tasks.addAll(repeatedTasks);

        return tasks;
    }

}
