package com.ivana.taskManager.repository;

import com.ivana.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
