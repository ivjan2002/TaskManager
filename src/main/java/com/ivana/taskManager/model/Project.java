package com.ivana.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="project_id")
    private int id;


    @Column
    @JsonProperty("projectName")
    @NonNull
    private int projectName;


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

    public void setId(int id){
        this.id=id;
    }
}
