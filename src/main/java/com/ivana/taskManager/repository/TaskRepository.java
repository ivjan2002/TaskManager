package com.ivana.taskManager.repository;

import com.ivana.taskManager.ENUM.TaskStatus;
import com.ivana.taskManager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.taskStatus = :status WHERE t.id = :taskId")
    void updateTaskStatus(@Param("taskId") int taskId, @Param("status") TaskStatus status);
}
