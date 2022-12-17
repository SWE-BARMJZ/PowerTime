package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.task.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    Authentication userAuthentication;
    public TodoService(){
        userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public List<Task> getTodos(){
        return null;
    }

}
