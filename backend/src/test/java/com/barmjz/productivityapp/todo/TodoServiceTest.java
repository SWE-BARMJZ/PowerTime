package com.barmjz.productivityapp.todo;

import com.barmjz.productivityapp.mind_map.repo.CategoryRepo;
import com.barmjz.productivityapp.mind_map.repo.OneTimeTaskRepo;
import com.barmjz.productivityapp.mind_map.repo.RepeatedTaskRepo;
import com.barmjz.productivityapp.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private OneTimeTaskRepo oneTimeTaskRepo;
    @Mock
    private RepeatedTaskRepo repeatedTaskRepo;
    @Mock
    private CategoryRepo categoryRepo;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoService(categoryRepo, oneTimeTaskRepo, repeatedTaskRepo);
    }
}
