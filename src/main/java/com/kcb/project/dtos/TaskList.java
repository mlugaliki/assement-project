package com.kcb.project.dtos;

import com.kcb.project.types.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskList {
    private Long count;
    private ProjectStatus status;

    public TaskList(Long count, ProjectStatus status) {
        this.count = count;
        this.status = status;
    }
}
