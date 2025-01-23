package com.ivana.taskManager.model;


import java.util.Date;

public class Task {

    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int userId;
    private int projectId;

    public int getId(){
        return id;
    }

}
