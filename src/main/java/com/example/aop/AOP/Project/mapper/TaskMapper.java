package com.example.aop.AOP.Project.mapper;

import com.example.aop.AOP.Project.entities.TaskDetails;
import com.example.aop.AOP.Project.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskDetails convertToTaskDetails(Task task){

        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTitle(task.getTitle());
        taskDetails.setDescription(task.getDescription());
        taskDetails.setStartDate(task.getStartDate());
        taskDetails.setEndDate(task.getEndDate());
        taskDetails.setTaskStatus(task.getTaskStatus());
        return taskDetails;
    }

    public Task convertToTaskDto(TaskDetails taskDetails){

        Task task = new Task();
        task.setId(taskDetails.getId());
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStartDate(taskDetails.getStartDate());
        task.setEndDate(taskDetails.getEndDate());
        task.setTaskStatus(taskDetails.getTaskStatus());
        return task;
    }

    private Date getDate(String date){
        try{
            if(StringUtils.isEmpty(date)){
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        }catch(ParseException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private String dateFormat(Date date){
        try {
            if(StringUtils.isEmpty(date)){
                return "Invalid date. Please enter valid date.";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Task> convertToTaskList(List<TaskDetails> taskList){
        return taskList.stream().map(task->convertToTaskDto(task)).collect(Collectors.toList());
    }
}