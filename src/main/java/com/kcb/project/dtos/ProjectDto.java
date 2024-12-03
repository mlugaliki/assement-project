package com.kcb.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProjectDto {
    @NotBlank(message = "Project name is required!")
    @Size(min = 3, message = "Project name must have at least 3 characters!")
    @Size(max = 20, message = "Project name can have at most 20 characters!")
    private String name;

    @NotBlank(message = "Project description is required!")
    @Size(min = 3, message = "Project description must have at least 3 characters!")
    private String description;

    private UUID projectId;
    private LocalDateTime createTime;
}
