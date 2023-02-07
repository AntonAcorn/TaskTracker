package ru.acorn.taskTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.enums.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private String taskName;

    private TaskStatus taskStatus;

    private Integer priority;

    private String taskDescription;

    private Project project;
}
