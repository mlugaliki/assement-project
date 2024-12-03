package com.kcb.project.controllers;

import com.kcb.project.dtos.TaskDto;
import com.kcb.project.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable UUID taskId) throws Exception {
        return ResponseEntity.ok(taskService.saveTask(taskDto, taskId));
    }

    @DeleteMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTaskInProject(@PathVariable UUID taskId) throws Exception {
         taskService.deleteTask(taskId);
         return ResponseEntity.ok().body("Task deleted successfully");
    }
}
