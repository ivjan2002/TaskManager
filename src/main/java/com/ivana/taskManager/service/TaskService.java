package com.ivana.taskManager.service;

import com.ivana.taskManager.model.Task;
import com.ivana.taskManager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getAll(){
        return taskRepository.findAll();

    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return taskRepository.existsById(id);
    }




}
