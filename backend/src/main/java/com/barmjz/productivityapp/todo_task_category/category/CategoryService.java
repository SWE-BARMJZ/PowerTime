package com.barmjz.productivityapp.todo_task_category.category;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    private final CategoryRepo categoryRepo;

    private final UserRepo userRepo;

    public Long createCategory(Category category){
        User user = userRepo.getUserByEmail(userAuthentication.getName()).orElse(null);
        category.setUser(user);
        if (categoryRepo.findAll().contains(category))
            return -1L;
        else
            categoryRepo.save(category);

        return categoryRepo
                .getCategoryByCategoryNameAndUser(category.getCategory_name(), user)
                .get()
                .getId();
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
