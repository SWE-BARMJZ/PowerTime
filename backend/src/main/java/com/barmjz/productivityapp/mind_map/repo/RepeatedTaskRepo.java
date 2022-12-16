package com.barmjz.productivityapp.mind_map.repo;

import com.barmjz.productivityapp.mind_map.model.RepeatedTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatedTaskRepo extends JpaRepository<RepeatedTask, Long> {
}
