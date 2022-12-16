package com.barmjz.productivityapp.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class TodoController {
    public ResponseEntity<String> getTodos(){
        return null;
    }

    public ResponseEntity<String> addOnetimeTask(){
        return null;
    }
    public ResponseEntity<String> addRepeatedTask(){
        return null;
    }

    public ResponseEntity<String> removeTodo(long id){
        return null;
    }

    public ResponseEntity<String> markTodo(long id){
        return null;
    }

    public ResponseEntity<String> categorizeTodos(long id){
        return null;
    }
}
