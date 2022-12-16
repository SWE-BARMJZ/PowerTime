package com.barmjz.productivityapp.mind_map.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTask {
    private String taskName;
    private String taskDesc;
}
