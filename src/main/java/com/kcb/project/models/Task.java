package com.kcb.project.models;

import com.kcb.project.types.ProjectStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Setter
@Getter
public class Task {
    @Id
    private UUID id;
    private String title;
    private String description;
    private ProjectStatus status;
    //@ManyToOne()
    private UUID projectId;
    private LocalDateTime dueDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
}
