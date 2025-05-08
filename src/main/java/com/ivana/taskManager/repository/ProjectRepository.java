package com.ivana.taskManager.repository;

import com.ivana.taskManager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

//repozi

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
