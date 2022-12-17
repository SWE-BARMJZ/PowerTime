package com.barmjz.productivityapp.category;

import com.barmjz.productivityapp.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> getCategoryByCategoryName(String categoryName);
}
