package ru.acorn.taskTracker.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.service.ProjectService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @RequestMapping("/create")
    public HttpEntity<?> createProject (@RequestBody Project project){
        projectService.createProject(project);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ProjectDTO viewProjectById (@PathVariable Long id){
       return projectService.viewProject(id);
    }

    @PatchMapping("/edit/{id}")
    public ProjectDTO editProjectById(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectToSave){
        return projectService.editProject(id, updatedProjectToSave);
    }


}
