package com.example.aop.AOP.Project.constraints;


import com.example.aop.AOP.Project.model.Task;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class CompareDateValidator implements ConstraintValidator<CompareDate, Task> {

    private String beforeFieldName;
    private String afterFieldName;

    @Override
    public void initialize(final CompareDate constraintAnnotation) {
        beforeFieldName = constraintAnnotation.before();
        afterFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(Task task, final ConstraintValidatorContext context) {
        try{
            final Field beforeDateField = task.getClass().getDeclaredField(beforeFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField  = task.getClass().getDeclaredField(afterFieldName);
            afterDateField.setAccessible(true);

            final LocalDate beforeDate  = (LocalDate) beforeDateField.get(task);
            final LocalDate afterDate = (LocalDate)  afterDateField.get(task);

            return beforeDate == null && afterDate == null || beforeDate !=null && beforeDate.isBefore(afterDate);

        }catch(final Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
