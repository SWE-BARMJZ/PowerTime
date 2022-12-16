package com.barmjz.productivityapp.mind_map.repo;

import com.barmjz.productivityapp.mind_map.model.OneTimeTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneTimeTaskRepo extends JpaRepository<OneTimeTask, Long> {
}
