package com.example.aop.AOP.Project.services;

import com.example.aop.AOP.Project.entities.TaskDetails;
import com.example.aop.AOP.Project.handler.TaskException;
import com.example.aop.AOP.Project.mapper.TaskMapper;
import com.example.aop.AOP.Project.model.Task;
import com.example.aop.AOP.Project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("taskService")
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
    @Transactional
    public Task createTask(Task task) {

        TaskDetails taskDetails = taskMapper.convertToTaskDetails(task);
        return taskMapper.convertToTaskDto(taskRepository.save(taskDetails));
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        //LocalDate.now(clock);

        Optional<TaskDetails> taskDetails = taskRepository.findById(id);

        if(!taskDetails.isPresent()){
            throw new TaskException(HttpStatus.NOT_FOUND,"Task Not Found with " + id);
        }

        TaskDetails task = new TaskDetails();
        taskRepository.delete(task);
    }

    @Override
    public Task getTask(Long id) {
        Task taskDetails = taskRepository.findById(id)
                .map(taskMapper::convertToTaskDto)
                .orElseThrow(() -> new TaskException(HttpStatus.NOT_FOUND,"Task Not found with " + id));
        return taskDetails;
        //if(taskDetails.isPresent()){
        //   return taskMapper.convertToTaskDto(taskDetails.get());
        //}
        //throw new TaskException(HttpStatus.NOT_FOUND,"Task Not found with " + id);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, Task task) {
        Optional<TaskDetails> taskDetails = taskRepository.findById(id);

        if(taskDetails.isPresent()){
            TaskDetails taskDetailsUpdate = taskMapper.convertToTaskDetails(task);
            taskDetailsUpdate.setId(id);
            return taskMapper.convertToTaskDto(taskRepository.save(taskDetailsUpdate));
        }
        throw new TaskException(HttpStatus.NOT_FOUND,"No Task Found with Id " + id);
    }

    @Override
    public List<Task> getAllTasks() {
        try {
            List<Task> tasks = taskMapper.convertToTaskList(taskRepository.findAll());
            return tasks;
        }catch(Exception ex){
            ex.printStackTrace();
            throw new TaskException(HttpStatus.NOT_FOUND, "No Task Found");
        }
    }
}