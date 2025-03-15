package com.ivana.taskManager.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivana.taskManager.ENUM.TaskPriority;
import com.ivana.taskManager.ENUM.TaskStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="task_id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Column
    @JsonProperty("taskName")
    @NonNull
    private String taskName;

    @Column
    @JsonProperty("description")
    @NonNull
    private String description;

    @Column
    @JsonProperty("startDate")
    @NonNull
    private Date startDate;

    @Column
    @JsonProperty("endDate")
    @NonNull
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column
    @JsonProperty("taskPriority")
    @NonNull
    private TaskPriority taskPriority;

    @Enumerated(EnumType.STRING)
    @Column
    @JsonProperty("taskStatus")
    @NonNull
    private TaskStatus taskStatus;

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }






//    @ManyToOne
//    @JoinColumn(name="project_id",nullable=false)
//    @JsonProperty("project")
//    private Project project;
//
//    @ManyToOne
//    @JoinColumn(name="user_id",nullable = false)
//    @JsonProperty("user")
//    private User user;








}
