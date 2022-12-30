package com.barmjz.productivityapp.pomodoro;
import com.barmjz.productivityapp.todo_task_category.task.Task;
import com.barmjz.productivityapp.user.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pomo")
@AllArgsConstructor
public class PomodoroController {

    private final PomodoroService pomoService;
    private final PomodoroRepo pomoRepo;

    @GetMapping("/")
    public ResponseEntity<Pomodoro> get() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<String> startStudy() {
        return null;
    }

    @PostMapping("/break")
    public ResponseEntity<String> startBreak() {return null;}

    @PostMapping("{remainingTime}/pause")
    public ResponseEntity<String> pause(@PathVariable Long remainingTime) {
        return null;
    }

    @PostMapping("/resume")
    public ResponseEntity<String> resume() {
        return null;
    }

    @PostMapping("/end")
    public ResponseEntity<String> end() {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<String> set(@RequestBody ObjectNode newPomo) {
        return null;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        return null;
    }
}
