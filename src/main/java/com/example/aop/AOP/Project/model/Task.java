package com.example.aop.AOP.Project.model;

import com.example.aop.AOP.Project.constraints.CompareDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Task Model")
@CompareDate(before = "startDate", after =  "endDate", message = "The start date must be before end date.")
@Component
public class Task {

    @ApiModelProperty(notes = "ID of the task", name = "id", required = true, value = "234567")
    private long id;

    @ApiModelProperty(notes = "Title of the task", name = "title", required = true, value = "Cleaning")
    @NotBlank(message = "Title must have a value")
    private String title;

    @NotBlank(message = "Description must have a value")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start Date field is a required field")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End Date is a required field.")
    private LocalDate endDate;

    @NotNull(message = "Task Status is a required field.")
    private TaskStatus taskStatus;

}