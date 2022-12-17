package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.mind_map.repo.CategoryRepo;
import com.barmjz.productivityapp.mind_map.repo.OneTimeTaskRepo;
import com.barmjz.productivityapp.mind_map.repo.RepeatedTaskRepo;
import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import com.barmjz.productivityapp.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private OneTimeTaskRepo oneTimeTaskRepo;
    @Mock
    private RepeatedTaskRepo repeatedTaskRepo;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private UserRepo userRepo;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoService(userRepo, categoryRepo, oneTimeTaskRepo, repeatedTaskRepo);
    }


}
