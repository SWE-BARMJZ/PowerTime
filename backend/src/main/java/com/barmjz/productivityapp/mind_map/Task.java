package com.barmjz.productivityapp.mind_map;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    private String taskDesc;
    private Date dueDate;
    private boolean isTodo;
}
