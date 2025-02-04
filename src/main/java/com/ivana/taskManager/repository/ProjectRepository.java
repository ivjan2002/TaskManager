package com.ivana.taskManager.repository;

import com.ivana.taskManager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
