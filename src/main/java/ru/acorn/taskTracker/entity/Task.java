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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String taskName;

    @NotEmpty
    private TaskStatus taskStatus;

    @NotNull
    private Integer priority;

    @NotEmpty
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Project project;
}
