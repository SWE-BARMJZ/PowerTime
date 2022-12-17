package com.barmjz.productivityapp.todo;
import com.barmjz.productivityapp.todo_mindmap.category.CategoryRepo;
import com.barmjz.productivityapp.todo_mindmap.task.OneTimeTaskRepo;
import com.barmjz.productivityapp.todo_mindmap.task.RepeatedTaskRepo;
import com.barmjz.productivityapp.todo_mindmap.todo.TodoService;
import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    OneTimeTaskRepo oneTimeTaskRepo;
    @Mock
    RepeatedTaskRepo repeatedTaskRepo;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private UserRepo userRepo;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
//        todoService = new TodoService(categoryRepo, oneTimeTaskRepo, repeatedTaskRepo);
        todoService = new TodoService(userRepo, categoryRepo, oneTimeTaskRepo, repeatedTaskRepo);
    }
}