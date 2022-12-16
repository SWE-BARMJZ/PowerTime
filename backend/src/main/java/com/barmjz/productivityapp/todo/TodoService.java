package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.mind_map.model.OneTimeTask;
import com.barmjz.productivityapp.mind_map.model.RepeatedTask;
import com.barmjz.productivityapp.mind_map.model.Task;
import com.barmjz.productivityapp.mind_map.repo.CategoryRepo;
import com.barmjz.productivityapp.mind_map.repo.OneTimeTaskRepo;
import com.barmjz.productivityapp.mind_map.repo.RepeatedTaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

    private final CategoryRepo categoryRepo;

    private final OneTimeTaskRepo oneTimeTaskRepo;

    private final RepeatedTaskRepo repeatedTaskRepo;


    public List<OneTimeTask> getOnetimeTask(){

    }

    public List<RepeatedTask> getRepeatedTask(){


    }


}
