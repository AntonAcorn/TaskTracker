package ru.acorn.taskTracker.entity;

import lombok.*;
import ru.acorn.taskTracker.entity.enums.TaskStatus;

import javax.persistence.*;
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
public class Task extends BaseIdEntity {

    @NotEmpty
    private String taskName;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @NotNull
    private Integer priority;

    @NotEmpty
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Project project;
}
