package com.ivana.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import com.ivana.taskManager.ENUM.Role;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="user_id")
    private int id;

    @Column
    @JsonProperty("userName")
    @NonNull
    private String userName;

    @Column
    @JsonProperty("email")
    @NonNull
    private String email;

    @Column
    @JsonProperty("password")
    @NonNull
    private String password;

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column
    @JsonProperty("role")
    @NonNull
    private Role role;

    public void setId(int id){
        this.id=id;
    }
}
