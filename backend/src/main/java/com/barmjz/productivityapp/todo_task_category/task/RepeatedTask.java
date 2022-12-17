package com.barmjz.productivityapp.todo_task_category.task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "repeated_task")
public class RepeatedTask extends Task {
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean sunday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean monday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean tuesday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean wednesday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean thursday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean friday;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean saturday;
    @JsonIgnore
    private Date lastRemovalDate;

}