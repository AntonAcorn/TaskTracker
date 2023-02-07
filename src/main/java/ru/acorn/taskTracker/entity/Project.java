package ru.acorn.taskTracker.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.acorn.taskTracker.entity.enums.ProjectStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project")
@Setter @Getter
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project extends BaseIdEntity{

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @CreationTimestamp
    private LocalDateTime startTimeOfProject;

    @NotNull
    private LocalDateTime completionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @NotNull
    private Integer priority;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> listOfTasks;
}
