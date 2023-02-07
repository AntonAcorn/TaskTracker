package ru.acorn.taskTracker.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.entity.enums.ProjectStatus;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProjectDTO {
    private String name;

    private LocalDateTime startTimeOfProject;

    private LocalDateTime completionDate;

    private ProjectStatus status;

    private Integer priority;

    private List<Task> listOfTasks;
}
