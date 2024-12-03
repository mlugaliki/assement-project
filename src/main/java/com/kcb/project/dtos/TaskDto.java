package com.kcb.project.dtos;

import com.kcb.project.types.ProjectStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class TaskDto {
    @NotBlank(message = "Task name is required!")
    @Size(min = 3, message = "Task name must have at least 3 characters!")
    private String title;

    @NotBlank(message = "Task description  is required!")
    @Size(min = 3, message = "Task description  must have at least 3 characters!")
    private String description;

    private ProjectStatus status;
    private LocalDateTime createTime;
    private String dueDate;
}
