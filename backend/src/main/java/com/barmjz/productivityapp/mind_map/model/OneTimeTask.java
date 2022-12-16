package com.barmjz.productivityapp.mind_map.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "one_time_task")
public class OneTimeTask extends Task {
    private Date dueDate;
    @ColumnDefault("null")
    private Date completionDate;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isToDo;
}
