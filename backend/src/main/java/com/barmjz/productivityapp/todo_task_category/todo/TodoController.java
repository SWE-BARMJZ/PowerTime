package com.barmjz.productivityapp.todo_task_category.todo;
import com.barmjz.productivityapp.todo_task_category.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;
    @GetMapping("/{date}")
    public ResponseEntity<List<Task>> getTodo(@PathVariable long date) {
        // tasks will be returned as sorted by due data ascendingly.
        // tasks having due date with null value will be returned first.
        try {
            return ResponseEntity.ok(todoService.getTasks(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> addToTodo(@PathVariable Long taskId){
       todoService.addTodoTask(taskId);
       return ResponseEntity.status(HttpStatus.OK).build();
    }
//    @PostMapping("/")
//    public ResponseEntity<String> createNewTodoTask(@RequestBody Task task, @RequestParam String type) {
//        return null;
//    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> removeFromTodo(@PathVariable Long taskId){
        // if task is one time task, we only update the isToDo flag to false
        // if task is repeated task, we update the value of lastRemovalDate to today.

        return null;
    }
}
