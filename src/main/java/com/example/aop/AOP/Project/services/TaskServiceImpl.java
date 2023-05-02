package com.example.aop.AOP.Project.services;

import com.example.aop.AOP.Project.entities.TaskDetails;
import com.example.aop.AOP.Project.handler.TaskException;
import com.example.aop.AOP.Project.mapper.TaskMapper;
import com.example.aop.AOP.Project.model.Task;
import com.example.aop.AOP.Project.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("taskService")
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    //private Clock clock;

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        //this.clock = clock;
    }

    @Override
    @Cacheable(value="tasks", key = "#id", unless = "#result==null")
    public Task getTask(Long id) {
        String method_GetTask = "getTask()";
        log.info("Entering method name: {}", method_GetTask);
        Task taskDetails = taskRepository.findById(id)
                .map(taskMapper::convertToTaskDto)
                .orElseThrow(() -> new TaskException(HttpStatus.NOT_FOUND,"Task Not found with " + id));
        log.info("Exiting method name: {}", method_GetTask);
        return taskDetails;
        //if(taskDetails.isPresent()){
        //   return taskMapper.convertToTaskDto(taskDetails.get());
        //}
        //throw new TaskException(HttpStatus.NOT_FOUND,"Task Not found with " + id);
    }

    @Override
    public List<Task> getAllTasks() {
        try {
            List<Task> tasks = taskMapper.convertToTaskList(taskRepository.findAll());
            return tasks;
        }catch(Exception ex){
            log.error("Error message: {}", ex);
            throw new TaskException(HttpStatus.NOT_FOUND, "No Task Found");
        }
    }

    @Override
    @Transactional
    @CachePut(value = "tasks", key = "#result.id") // key value uses the result keyword which is the task returned by this create method is stored in cache
    // and indexed by id attribute of the returned task
    public Task createTask(Task task) {
        String method_CreateTask = "createTask()";
        log.info("Entering method name: {}", method_CreateTask);
        TaskDetails taskDetails = taskMapper.convertToTaskDetails(task);
        Task taskCreated = taskMapper.convertToTaskDto(taskRepository.save(taskDetails));
        log.info("Exiting method name: {}", method_CreateTask);
        return taskCreated;

    }

    @Override
    @Transactional
    @CachePut(value = "tasks", key = "#task.id")
    public Task updateTask(Long id, Task task) {
        String method_UpdateTask = "updateTask()";
        log.info("Entering method name: {}", method_UpdateTask);
        Optional<TaskDetails> taskDetails = taskRepository.findById(id);

        if(taskDetails.isPresent()){
            TaskDetails taskDetailsUpdate = taskDetails.get();
            taskDetailsUpdate.setTitle(task.getTitle());
            taskDetailsUpdate.setDescription(task.getDescription());
            taskDetailsUpdate.setStartDate(task.getStartDate());
            taskDetailsUpdate.setEndDate(task.getEndDate());
            taskDetailsUpdate.setTaskStatus(task.getTaskStatus());
            return taskMapper.convertToTaskDto(taskRepository.save(taskDetailsUpdate));
        }
        throw new TaskException(HttpStatus.NOT_FOUND,"No Task Found with Id " + id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTask(Long id) {
        //LocalDate.now(clock);
        String method_DeleteTask =  "deleteTask()";
        log.info("Entering method name: {}", method_DeleteTask);
        Optional<TaskDetails> taskDetails = taskRepository.findById(id);

        if(!taskDetails.isPresent()){
            throw new TaskException(HttpStatus.NOT_FOUND,"Task Not Found with " + id);
        }

        TaskDetails task = taskDetails.get();
        taskRepository.delete(task);
    }

    @Override
    @CacheEvict(
            value = "tasks",
            allEntries = true)
    public String evictCache() {
        String method_evictCache = "evictCache()";
        log.info("Entering method name: {}", method_evictCache);
        return "Cache Cleared";
    }
}