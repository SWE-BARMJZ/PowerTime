package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.mind_map.model.Task;
import lombok.AllArgsConstructor;
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
