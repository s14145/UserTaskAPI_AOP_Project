package com.example.aop.AOP.Project.controller;

import com.example.aop.AOP.Project.handler.ErrorResponse;
import com.example.aop.AOP.Project.model.Task;
import com.example.aop.AOP.Project.services.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiOperation(value = "/task", tags = "Task Controller")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "Insert Task Record", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @PostMapping(value="/createTask",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@ApiParam(value = "TASK") @Valid @RequestBody Task task){
        Task taskResponse = taskService.createTask(task);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete Task Record", response = Task.class)
    @DeleteMapping(value="/deleteTask/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> deleteTask(@ApiParam(value= "Task ID", required = true) @PathVariable Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve All Tasks", response = Task.class)
    @GetMapping(value="/getAllTasks",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasksResponse = taskService.getAllTasks();
        return new ResponseEntity<>(allTasksResponse,HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve Single Task", response = Task.class)
    @GetMapping(value="/getTask/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@ApiParam(value = "Task ID",required = true) @PathVariable Long id){
        return new ResponseEntity<>(taskService.getTask(id),HttpStatus.OK);
    }
}