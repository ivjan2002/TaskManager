package com.ivana.taskManager.service;

import com.ivana.taskManager.model.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class TaskService {
    ArrayList<Task> taskArray=new ArrayList<>();

    public ArrayList<Task> getAllTasks(){
        return taskArray;
    }

    public Task getTaskById(int id){
        for(int i=0;i<taskArray.size();i++){
            if(taskArray.get(i).getId() == id){
                return taskArray.get(i);
            }
        }
        return null;
    }

    public Task createTask(Task task) {
        taskArray.add(task);
        return task;
    }



}
