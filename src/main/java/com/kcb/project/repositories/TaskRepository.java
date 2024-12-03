package com.kcb.project.repositories;

import com.kcb.project.dtos.TaskList;
import com.kcb.project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT t FROM Task t WHERE t.projectId=:projectId")
    List<Task> findByProjectId(UUID projectId);

    @Query("SELECT new com.kcb.project.dtos.TaskList(COUNT(t.id), t.status) FROM Task t WHERE t.projectId=:projectId GROUP BY t.status")
    List<TaskList> getTaskSummary(UUID projectId);
}
