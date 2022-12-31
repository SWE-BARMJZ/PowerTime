package com.barmjz.productivityapp.todo_task_category.category;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    private final UserRepo userRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, UserRepo userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public Long createCategory(Category category){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByEmail(auth.getName()).orElseThrow();

        boolean nameAlreadyExists = categoryRepo.getCategoryByCategoryNameAndUser(category.getCategory_name(), user).isPresent();
        if (nameAlreadyExists)
            throw new IllegalArgumentException("A Category with the same name already exists");

        category.setUser(user);
        categoryRepo.save(category);

        return category.getId();
    }

    public String editCategory(long categoryId, String category){
        categoryRepo.renameCategory(category, categoryId);
        return "Edited";
    }

    public String deleteCategory(long categoryId){
        categoryRepo.deleteById(categoryId);
        return "Deleted";
    }
}
