package com.barmjz.productivityapp.todo_task_category.category;

import com.barmjz.productivityapp.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private Authentication userAuthentication;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategory() {
    }
}