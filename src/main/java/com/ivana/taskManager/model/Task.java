package com.ivana.taskManager.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Task {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column
    private int id;

    @Column
    @JsonProperty("taskName")
    private String taskName;

    @Column
    @JsonProperty("description")
    private String description;

    @Column
    @JsonProperty("startDate")
    private Date startDate;

    @Column
    @JsonProperty("endDate")
    private Date endDate;


    //private int userId;


    //private int projectId;



}
