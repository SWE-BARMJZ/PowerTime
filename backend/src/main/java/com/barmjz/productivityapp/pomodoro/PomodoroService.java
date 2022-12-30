package com.barmjz.productivityapp.pomodoro;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@AllArgsConstructor
public class PomodoroService {
    private final PomodoroRepo pomoRepo;


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


    
}
