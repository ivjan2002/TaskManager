package com.ivana.taskManager.service;


import com.ivana.taskManager.model.Project;
import com.ivana.taskManager.repository.ProjectRepository;
import com.ivana.taskManager.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private JwtUtils jwtUtils;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,JwtUtils jwtUtils){
        this.projectRepository=projectRepository;
        this.jwtUtils=jwtUtils;

    }

    private boolean isAdmin(String token) {
        String role = jwtUtils.getRoleFromToken(token);
        return "ADMIN".equals(role);
    }

    public Project addProject(Project project, String token){
        if (!isAdmin(token)) {
            throw new SecurityException("Only admin can add projects");
        }
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){

        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Integer id){
        return
                projectRepository.findById(id);
    }

    public void deleteProjectById(Integer id, String token){
        if (!isAdmin(token)) {
            throw new SecurityException("Only admin can delete projects");
        }
        projectRepository.deleteById(id);
    }

    public boolean existById(Integer id){

        return projectRepository.existsById(id);
    }

    public Project updateProject(Integer id, Project updatedProject, String token) {
        if (!isAdmin(token)) {
            throw new SecurityException("Only admin can update projects");
        }

        if (projectRepository.existsById(id)) {
            updatedProject.setId(id);
            return projectRepository.save(updatedProject);
        } else {
            throw new IllegalArgumentException("Project with ID " + id + " does not exist.");
        }
    }


}
