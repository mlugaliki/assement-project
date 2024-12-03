package com.kcb.project.services.impl;

import com.kcb.project.dtos.TaskDto;
import com.kcb.project.helpers.DateHelpers;
import com.kcb.project.models.Task;
import com.kcb.project.repositories.TaskRepository;
import com.kcb.project.services.TaskService;
import com.kcb.project.types.ProjectStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> getTaskById(UUID taskId) {
        return this.taskRepository.findById(taskId);
    }

    @Override
    public ResponseEntity<List<Task>> getTasksByProjectId(UUID projectId) {
         return ResponseEntity.ok(taskRepository.findByProjectId(projectId));
    }

    @Override
    public Task saveTask(TaskDto taskDto, UUID projectId, UUID taskId) throws Exception {
        Task task = taskRepository.findById(taskId).orElse(null);
        if(task != null) {
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            LocalDateTime dueDate = LocalDateTime.parse(taskDto.getDueDate(),DateHelpers.DATE_FORMATTER);
            task.setDueDate(dueDate);
            task.setUpdateTime(LocalDateTime.now());
            return taskRepository.save(task);
        }

       throw new Exception(String.format("Task with Id %s not found", taskId));
    }

    @Override
    public Task saveTask(TaskDto taskDto, UUID projectId) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setProjectId(projectId);
        task.setStatus(taskDto.getStatus());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCreateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if(taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setDeleteTime(LocalDateTime.now());
            taskRepository.save(task);
        }
    }
}
