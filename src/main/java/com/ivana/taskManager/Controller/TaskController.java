package com.ivana.taskManager.Controller;

import com.ivana.taskManager.ENUM.TaskStatus;
import com.ivana.taskManager.model.Task;
import com.ivana.taskManager.security.JwtUtils;
import com.ivana.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    private JwtUtils jwtUtils;

    @Autowired
    public TaskController(TaskService taskService,JwtUtils jwtUtils){
        this.taskService=taskService;
        this.jwtUtils=jwtUtils;
    }

    @GetMapping
    public ResponseEntity<?> getTasks(@RequestHeader("Authorization") String token) {
        try {
            if (!jwtUtils.validateJwtToken(token.substring(7))) {
                return ResponseEntity.status(401).body("Invalid token");
            }

            List<Task> tasks = taskService.getAll();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Access denied");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id){
        if (!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id,@RequestBody Task updatedTask){
        try{
            Task task=taskService.updateTask(id,updatedTask);
            return ResponseEntity.ok(updatedTask);
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable int id, @RequestParam TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated successfully");
    }


    }








