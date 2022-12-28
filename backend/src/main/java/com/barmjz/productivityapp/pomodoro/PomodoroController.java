package com.barmjz.productivityapp.pomodoro;
import com.barmjz.productivityapp.todo_task_category.task.Task;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pomo")
@AllArgsConstructor
public class PomodoroController {

    private final PomodoroService pomoService;

    @GetMapping("/")
    public ResponseEntity<Pomodoro> getPomodoro() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<String> startStudy() {
        return null;
    }

    @PostMapping("/break")
    public ResponseEntity<String> startBreak() {
        return null;
    }

    @PostMapping("/{remainingTime}/pause")
    public ResponseEntity<String> pause(@PathVariable Long remainingTime) {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<String> setPomodoro(@RequestBody ObjectNode newPomo) {
        return null;
    }


//    @DeleteMapping("/{taskId}")
//    public ResponseEntity<Task> removeFromTodo(@PathVariable Long taskId, @RequestParam String taskType){
//
//    }
}
