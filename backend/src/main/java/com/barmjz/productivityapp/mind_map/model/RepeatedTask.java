package com.barmjz.productivityapp.mind_map.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repeated_task")
public class RepeatedTask extends Task {
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean sunday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean monday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean tuesday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean wednesday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean thursday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean friday;
    @Column(nullable = false, columnDefinition = "default 0")
    private Boolean saturday;
    private Date lastRemovalDate;
}
