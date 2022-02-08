package com.example.aop.AOP.Project.repository;

import com.example.aop.AOP.Project.entities.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDetails, Long> {
}