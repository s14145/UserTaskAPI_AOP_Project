package com.example.aop.AOP.Project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Task Model")
public class Task {

    @ApiModelProperty(notes = "ID of the task", name = "id", required = true, value = "234567")
    private long id;

    @ApiModelProperty(notes = "Title of the task", name = "title", required = true, value = "Cleaning")
    private String title;
    private String Description;
    private Date startDate;
    private Date endDate;
    private TaskStatus taskStatus;

}
