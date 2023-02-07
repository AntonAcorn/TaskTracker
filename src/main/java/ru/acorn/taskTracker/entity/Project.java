package ru.acorn.taskTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.acorn.taskTracker.entity.enums.ProjectStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@Setter @Getter
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Task> listOfTasks;
}
