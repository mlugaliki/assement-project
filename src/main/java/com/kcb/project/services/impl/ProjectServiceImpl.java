package com.kcb.project.services.impl;

import com.kcb.project.dtos.ProjectDto;
import com.kcb.project.dtos.SummaryDto;
import com.kcb.project.models.Project;
import com.kcb.project.repositories.ProjectRepository;
import com.kcb.project.repositories.TaskRepository;
import com.kcb.project.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Project save(ProjectDto projectDto) throws Exception {
        if (this.getProjectByName(projectDto.getName()).isPresent()) {
            throw new Exception("Project already exists");
        }

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setId(UUID.randomUUID());
        return projectRepository.save(project);
    }

    @Override
    public Project save(ProjectDto projectDto, UUID projectId) throws Exception {
        Optional<Project> savedProjectOpt = this.projectRepository.findById(projectId);
        if (savedProjectOpt.isPresent()) {
            Project savedProject = savedProjectOpt.get();
            savedProject.setName(projectDto.getName());
            savedProject.setDescription(projectDto.getDescription());
            savedProject.setUpdateTime(LocalDateTime.now());
            return this.projectRepository.save(savedProject);
        }

        throw new Exception("Project not found");
    }

    @Override
    public ResponseEntity<Optional<Project>> getProjectById(UUID projectId) {
        return ResponseEntity.ok(this.projectRepository.findById(projectId));
    }

    @Override
    public Optional<Project> getProjectByName(String projectName) {
        return this.projectRepository.findByProjectName(projectName.toLowerCase());
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = this.projectRepository.findAll();
        return ResponseEntity.ok().body(projects);
    }

    @Override
    public ResponseEntity<?> getSummary() {
        List<Project> projects = this.projectRepository.findAll();
        List<SummaryDto> summaryDtos = new ArrayList<>();
        for (Project project : projects) {
            summaryDtos.add(new SummaryDto(project.getName(), taskRepository.getTaskSummary(project.getId())));

        }
        return ResponseEntity.ok(summaryDtos);
    }
}
