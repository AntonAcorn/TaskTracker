package ru.acorn.taskTracker.entity;

import lombok.*;
import ru.acorn.taskTracker.entity.enums.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task")
@Setter
@Getter
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Task name should not be empty")
    private String taskName;

    @NotNull(message = "Project status should not be null")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @NotNull(message = "Priority should not be empty")
    @Min(value = 1, message = "Priority should not be less then 1")
    @Max(value = 10, message = "Priority should not be more then 10")
    private Integer priority;

    @NotEmpty(message = "Task description should not be empty")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
}
