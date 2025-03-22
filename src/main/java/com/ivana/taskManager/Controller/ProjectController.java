package com.ivana.taskManager.Controller;

import com.ivana.taskManager.model.Project;
import com.ivana.taskManager.security.JwtUtils;
import com.ivana.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;
    private JwtUtils jwtUtils;


    @Autowired
    public ProjectController(ProjectService projectService,JwtUtils jwtUtils){

        this.projectService=projectService;
        this.jwtUtils=jwtUtils;
    }

    @GetMapping
    public ResponseEntity<?> getProjects(@RequestHeader("Authorization") String token) {
        try {
            if (!jwtUtils.validateJwtToken(token.substring(7))) {
                return ResponseEntity.status(401).body("Invalid token");
            }

            List<Project> projects = projectService.getAllProjects();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Access denied");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        try {

            if (!jwtUtils.validateJwtToken(token.substring(7))) {
                return ResponseEntity.status(401).body("Invalid token");
            }

            return projectService.getProjectById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Access denied");
        }
    }


    @PostMapping("/addProject")
    public ResponseEntity<?> add(@RequestBody Project project, @RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(projectService.addProject(project, token.substring(7)));
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access denied: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if(!projectService.existById(id)){
            return ResponseEntity.notFound().build();
        }
        try {
            projectService.deleteProjectById(id, token.substring(7));
            return ResponseEntity.noContent().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject, @RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(projectService.updateProject(id, updatedProject, token.substring(7))); // Isečemo "Bearer " iz tokena
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access denied: " + e.getMessage());
        }
    }



}

