package com.example.aop.AOP.Project.services;


import com.example.aop.AOP.Project.model.Task;

import java.util.List;

public interface TaskService {

    public Task createTask(Task task);

    public void deleteTask(Long id);

    public Task getTask(Long id);

    public Task updateTask(Long id,Task task);

    public List<Task> getAllTasks();

    public String evictCache();
}