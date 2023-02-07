package ru.acorn.taskTracker.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.service.ProjectService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @RequestMapping("/create")
    public HttpEntity<?> createProject(@RequestBody Project project) {
        projectService.createProject(project);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ProjectDTO viewProjectById(@PathVariable Long id) {
        return projectService.viewProject(id);
    }

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
        } else if(isStartTime){
            return projectService.findAllByOrderByStartTimeOfProject();
        }
        return projectService.viewAllProjects();
    }

    @GetMapping("/getTasks/{projectId}")
    public List<Task> viewAllTasksOfProjectById(@PathVariable Long projectId) {
        return projectService.viewAllTasksOfProjectById(projectId);
    }

    @PatchMapping("/edit/{id}")
    public ProjectDTO editProjectById(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectToSave) {
        return projectService.editProject(id, updatedProjectToSave);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.ok().build();
    }
}
