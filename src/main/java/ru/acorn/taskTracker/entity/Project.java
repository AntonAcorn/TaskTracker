package ru.acorn.taskTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.acorn.taskTracker.entity.enums.ProjectStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startTimeOfProject;

    @NotNull(message = "Completion Date should not be empty")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime completionDate;

    @NotNull(message = "Project status should not be null")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @NotNull(message = "Priority should not be empty")
    @Min(value = 1, message = "Priority should not be less then 1")
    @Max(value = 10, message = "Priority should not be more then 10")
    private Integer priority;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Task> listOfTasks;
}
