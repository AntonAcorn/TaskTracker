package ru.acorn.taskTracker.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Project project;
}
