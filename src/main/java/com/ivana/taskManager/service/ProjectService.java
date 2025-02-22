package com.ivana.taskManager.service;


import com.ivana.taskManager.model.Project;
import com.ivana.taskManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;

    }

    public Project addProject(Project project){

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){

        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Integer id){
        return
                projectRepository.findById(id);
    }

    public void deleteProjectById(Integer id){
        projectRepository.deleteById(id);
    }

    public boolean existById(Integer id){

        return projectRepository.existsById(id);
    }

    public Project updateProject(Integer id, Project updatedProject) {
        if (projectRepository.existsById(id)) {
            updatedProject.setId(id);
            return projectRepository.save(updatedProject);
        } else {
            throw new IllegalArgumentException("Project with ID " + id + " does not exist.");
        }
    }


}
