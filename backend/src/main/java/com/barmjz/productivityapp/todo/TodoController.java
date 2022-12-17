package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/todo")
public class TodoController {

    @GetMapping("/")
    public ResponseEntity<List<Task>> getTodo(){
        // tasks will be returned as sorted by due data ascendingly.
        // tasks having due date with null value will be returned first.
        return null;
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> addToTodo(@PathVariable Long taskId){
        return null;
    }
    @PostMapping("/")
    public ResponseEntity<String> createNewTodoTask(@RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> removeFromTodo(@PathVariable Long taskId){
        // if task is one time task, we only update the isToDo flag to false
        // if task is repeated task, we update the value of lastRemovalDate to today.
        return null;
    }
}
