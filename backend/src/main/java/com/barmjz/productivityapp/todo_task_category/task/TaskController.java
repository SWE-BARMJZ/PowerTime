package com.barmjz.productivityapp.todo_task_category.task;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public List<CategorisedTasks> getAllCategorisedTasks() {
        return taskService.getAllTasksCategorised();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        try {
            return ResponseEntity.ok(taskService.getTask(taskId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.updateTask(taskId, task));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/")
    public ResponseEntity<Long> createTask(@RequestBody TaskCreationDTO taskDTO, @RequestParam String taskType) {
        try {
            Long createdTaskId = taskType.equals("onetime")
                    ? taskService.createOneTimeTask(taskDTO)
                    : taskService.createRepeatedTask(taskDTO);

            return ResponseEntity.ok(createdTaskId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{taskId}/tick")
    public ResponseEntity<Task> tickTask(@PathVariable Long taskId, @RequestParam Long date, @RequestParam String taskType) {
        try {
            return ResponseEntity.ok(taskService.tickTask(taskId, date, taskType));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{taskId}/untick")
    public ResponseEntity<Task> untickTask(@PathVariable Long taskId, @RequestParam Long date) {
        try {
            return ResponseEntity.ok(taskService.untickTask(taskId, date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        try {
            return ResponseEntity.ok(taskService.getCompletedTasks());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
