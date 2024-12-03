package com.kcb.project.services;

import com.kcb.project.dtos.ProjectDto;
import com.kcb.project.models.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {
    /**
     * Saves new project
     *
     * @param project
     * @return
     * @throws Exception
     */
    Project save(ProjectDto project) throws Exception;

    /**
     * Updates existing project
     *
     * @param project
     * @param projectId
     * @return
     * @throws Exception
     */
    Project save(ProjectDto project, UUID projectId) throws Exception;

    /**
     * Get project using Id
     *
     * @param projectId
     * @return
     */
    ResponseEntity<Optional<Project>> getProjectById(UUID projectId);

    /**
     * Gets project using name
     *
     * @param projectName
     * @return
     */
    Optional<Project> getProjectByName(String projectName);

    /**
     * List all saved projects
     *
     * @return
     */
    ResponseEntity<List<Project>> getAllProjects();

    ResponseEntity<?> getSummary();
}
