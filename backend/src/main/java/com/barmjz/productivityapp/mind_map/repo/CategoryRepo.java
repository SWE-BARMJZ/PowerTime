package com.barmjz.productivityapp.mind_map.repo;

import com.barmjz.productivityapp.mind_map.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
