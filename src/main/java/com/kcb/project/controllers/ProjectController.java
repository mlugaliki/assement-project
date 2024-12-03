package com.kcb.project.controllers;


import com.kcb.project.dtos.ProjectDto;
import com.kcb.project.dtos.TaskDto;
import com.kcb.project.services.ProjectService;
import com.kcb.project.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNewProject(@Valid @RequestBody ProjectDto projectDto) throws Exception {
        projectService.save(projectDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProject() throws Exception {
        return projectService.getAllProjects();
    }

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable UUID projectId) throws Exception {
        return projectService.getProjectById(projectId);
    }


    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDto projectDto, @PathVariable UUID projectId) throws Exception {
        projectService.save(projectDto, projectId);
        return ResponseEntity.ok().body("Project updated successfully");
    }


    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<?> saveTask(@Valid @RequestBody TaskDto taskDto, @PathVariable UUID projectId) throws Exception {
        taskService.saveTask(taskDto, projectId);
        return ResponseEntity.ok().body("Project updated successfully");
    }

    @GetMapping(value = "/{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTaskInProject(@PathVariable UUID projectId) throws Exception {
        return taskService.getTasksByProjectId(projectId);
    }


    @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSummary() throws Exception {
        return projectService.getSummary();
    }
}
