package com.barmjz.productivityapp.todo_task_category.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Long createCategory(Category category){
        if (categoryRepo.findAll().contains(category))
            return -1L;
        else
            categoryRepo.save(category);
        return categoryRepo
                .getCategoryByCategoryName(category.getCategoryName())
                .get()
                .getId();
    }

    public void editCategory(long categoryId, Category category){
        categoryRepo.save(category);
    }

    public void deleteCategory(long categoryId){
        categoryRepo.deleteById(categoryId);
    }

}
