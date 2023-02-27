package com.barmjz.productivityapp.todo_task_category.task;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTask {
    @Column(nullable = false)
    @JsonProperty("name")
    private String taskName;

    @JsonProperty("description")
    private String taskDesc;
}