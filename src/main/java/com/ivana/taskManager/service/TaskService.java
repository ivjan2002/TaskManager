package com.ivana.taskManager.service;

import com.ivana.taskManager.ENUM.TaskStatus;
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

    public Task updateTask(Integer id,Task updatedTask){
        if(taskRepository.existsById(id)){
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        }
        else{
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }

    }

    @Transactional
    public void updateTaskStatus(int taskId, TaskStatus newStatus) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTaskStatus(newStatus);
            taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " not found");
        }
    }

}
