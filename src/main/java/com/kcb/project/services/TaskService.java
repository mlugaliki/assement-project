package com.kcb.project.services;

import com.kcb.project.dtos.TaskDto;
import com.kcb.project.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    /**
     * Gets a task by id
     * @param id
     * @return
     */
    Optional<Task> getTaskById(UUID id);

    /**
     * Gets all tasks in a project
     * @param projectId
     * @return
     */
    ResponseEntity<List<Task>> getTasksByProjectId(UUID projectId);


    /**
     * Updates an existing task
     * @param task
     * @param projectId
     * @param taskId
     * @return
     */
    Task saveTask(TaskDto task, UUID projectId, UUID taskId) throws Exception;

    /**
     *
     * @param saves a new task
     * @return Task {{@Task}}
     */
    Task saveTask(TaskDto task, UUID projectId);

    /**
     * Deletes an existing task
     * @param id
     */
    void deleteTask(UUID id);
}
