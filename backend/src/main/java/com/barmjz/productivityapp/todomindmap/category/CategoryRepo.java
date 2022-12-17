package com.barmjz.productivityapp.todomindmap.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> getCategoryByCategoryName(String categoryName);
}
