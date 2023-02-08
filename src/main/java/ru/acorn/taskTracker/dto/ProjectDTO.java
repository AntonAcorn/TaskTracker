package ru.acorn.taskTracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.entity.enums.ProjectStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Project dto")
public class ProjectDTO {

    private String projectName;

    private LocalDateTime startTimeOfProject;

    private LocalDateTime completionDate;

    private ProjectStatus status;

    @Min(1)
    @Max(10)
    private Integer priority;

    private List<Task> listOfTasks;
}
