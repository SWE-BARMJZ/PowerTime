package com.barmjz.productivityapp.mind_map.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTask {
    @Column(nullable = false, unique = true)
    private String taskName;
    private String taskDesc;
}
