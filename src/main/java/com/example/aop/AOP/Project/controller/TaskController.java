package com.example.aop.AOP.Project.controller;

import com.example.aop.AOP.Project.error.ErrorResponse;
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
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiOperation(value = "/v1", tags = "Task Controller")
@RestController
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "Retrieve Single Task", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @GetMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@ApiParam(value = "Task ID", required = true) @PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve All Tasks", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasksResponse = taskService.getAllTasks();
        return new ResponseEntity<>(allTasksResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Insert Task Record", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 201, message = "CREATED", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @PostMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@ApiParam(value = "TASK") @Valid @RequestBody Task task) {
        Task taskResponse = taskService.createTask(task);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Task Record", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 201, message = "CREATED", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @PutMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@ApiParam(value = "Task ID", required = true)@PathVariable long id, @ApiParam(value = "TASK") @Valid @RequestBody Task task){
      Task taskResponse = taskService.updateTask(id, task);
      return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Task Record", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Task.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorResponse.class)
    })
    @DeleteMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTask(@ApiParam(value = "Task ID", required = true) @PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Clear All Caches", response = Task.class)
    @GetMapping(value = "/clearCache", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> clearCache() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Clear Cache Task");
        String stringResponseEntity = taskService.evictCache();
        stopWatch.stop();
        System.out.println("Total Time taken for clearing cache" + stopWatch.getTotalTimeMillis());
        return new ResponseEntity<>(stringResponseEntity, HttpStatus.OK);
    }
}