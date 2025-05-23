package com.ivana.taskManager.repository;

import com.ivana.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);

    User findByUserName(String userName);


}
