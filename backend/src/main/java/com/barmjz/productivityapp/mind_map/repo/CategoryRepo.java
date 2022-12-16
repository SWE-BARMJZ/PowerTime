package com.barmjz.productivityapp.mind_map.repo;

import com.barmjz.productivityapp.mind_map.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
