package ru.acorn.taskTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.exception.ProjectNotFoundException;
import ru.acorn.taskTracker.repository.ProjectRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Ability to create / view / edit / delete information about projects

    @Transactional
    public void createProject (Project project){
        projectRepository.save(project);
    }

    public Project viewProject(Long id){
        Optional<Project> projectToBeFound = projectRepository.findById(id);
        return projectToBeFound.orElseThrow(ProjectNotFoundException::new);
    }
}
