package com.barmjz.productivityapp.mind_map.model;


import com.barmjz.productivityapp.user.User;
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
    private Date completionDate;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isToDo;

}
