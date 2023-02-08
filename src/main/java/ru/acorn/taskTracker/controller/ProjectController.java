package ru.acorn.taskTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.service.ProjectService;
import ru.acorn.taskTracker.utils.ErrorUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@Log4j
@Tag(
        name = "Projects",
        description = "All methods for working with projects"
)
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Creation of project")
    @PostMapping
    @RequestMapping("/create")
    public HttpEntity<?> createProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult);
            ErrorUtils.returnError(bindingResult);
        }
        projectService.createProject(project);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Find project by Id")
    @GetMapping("/{id}")
    public ProjectDTO viewProjectById(@PathVariable Long id) {
        return projectService.viewProject(id);
    }

    @Operation(summary = "Find all and filter or order projects by startAt, endWith, priority, startTime")
    @GetMapping()
    public List<Project> viewAllProjects(@RequestParam(value = "startAt", required = false) String startAt,
                                         @RequestParam(value = "endWith", required = false) String endWith,
                                         @RequestParam(value = "priority", required = false) boolean isPriority,
                                         @RequestParam(value = "startTime", required = false) boolean isStartTime) {
        if (startAt != null) {
            return projectService.viewAllProjectsStartAt(startAt);
        } else if (endWith != null) {
            return projectService.viewAllProjectsEndWith(endWith);
        } else if (isPriority) {
            return projectService.viewAllByOrderByPriority();
        } else if (isStartTime) {
            return projectService.findAllByOrderByStartTimeOfProject();
        }
        return projectService.viewAllProjects();
    }

    @Operation(summary = "Find all tasks by project id")
    @GetMapping("/getTasks/{projectId}")
    public List<Task> viewAllTasksOfProjectById(@PathVariable Long projectId) {
        return projectService.viewAllTasksOfProjectById(projectId);
    }

    @Operation(summary = "Edit project by id")
    @PatchMapping("/edit/{id}")
    public ProjectDTO editProjectById(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectToSave) {
        return projectService.editProject(id, updatedProjectToSave);
    }

    @Operation(summary = "Delete project by id")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.ok().build();
    }
}
