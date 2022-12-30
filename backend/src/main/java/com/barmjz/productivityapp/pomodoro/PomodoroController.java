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

    @GetMapping("/{userId}")
    public ResponseEntity<Pomodoro> get(@PathVariable Long userId) {
        return null;
    }

    @PostMapping("/{pomoId}")
    public ResponseEntity<String> startStudy(@PathVariable Long pomoId) {
        return null;
    }

    @PostMapping("/{pomoId}/break")
    public ResponseEntity<String> startBreak(@PathVariable Long pomoId) {
        return null;
    }

    @PostMapping("/{pomoId}/pause")
    public ResponseEntity<String> pause(@PathVariable Long pomoId,@RequestParam Long remainingTime) {
        return null;
    }

    @PostMapping("/{pomoId}/resume")
    public ResponseEntity<String> resume(@PathVariable Long pomoId) {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<String> set(@RequestBody ObjectNode newPomo) {
        return null;
    }

}
