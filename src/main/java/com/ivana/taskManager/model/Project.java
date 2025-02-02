//package com.ivana.taskManager.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.Id;
//
//import java.util.Date;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table
//public class Project {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonProperty("id")
//    @Column(name="projectId")
//    private int id;
//
//    @Column
//    @JsonProperty("projectName")
//    private int projectName;
//
//    @Column
//    @JsonProperty("description")
//    private String description;
//
//    @Column
//    @JsonProperty("startDate")
//    private Date startDate;
//
//    @Column
//    @JsonProperty("endDate")
//    private Date endDate;
//}
