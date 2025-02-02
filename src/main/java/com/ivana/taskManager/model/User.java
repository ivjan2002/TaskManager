package com.ivana.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column
    private int id;

    @Column
    @JsonProperty("userName")
    private int userName;

    @Column
    @JsonProperty("email")
    private String email;

    @Column
    @JsonProperty("password")
    private String password;

}
