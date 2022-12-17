package com.barmjz.productivityapp.todo_task_category.task;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;


@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "one_time_task")
public class OneTimeTask extends Task {
    private Date dueDate;
    @ColumnDefault("null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date completionDate;
    @Column(nullable = false)
    @ColumnDefault("false")
    @JsonIgnore
    private boolean todo;
}
