package ru.acorn.taskTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.exception.ProjectNotFoundException;
import ru.acorn.taskTracker.repository.ProjectRepository;

import java.time.LocalDateTime;
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
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    public ProjectDTO viewProject(Long id) {
        Optional<Project> projectToBeFound = projectRepository.findById(id);
        if (projectToBeFound.isPresent()) {
            var resultProject = projectToBeFound.get();
            return ProjectDTO.builder()
                    .name(resultProject.getName())
                    .startTimeOfProject(LocalDateTime.now())
                    .completionDate(resultProject.getCompletionDate())
                    .status(resultProject.getStatus())
                    .priority(resultProject.getPriority())
                    .listOfTasks(resultProject.getListOfTasks())
                    .build();
        } else {
            throw new ProjectNotFoundException();
        }
    }

    public ProjectDTO editProject(Long id, ProjectDTO projectDTOToReturn) {
        var projectToBeFoundById = projectRepository.findById(id);
        if (projectToBeFoundById.isPresent()) {
            var resultProject = projectToBeFoundById.get();
            resultProject = Project.builder()
                    .name(projectDTOToReturn.getName())
                    .startTimeOfProject(projectDTOToReturn.getStartTimeOfProject())
                    .completionDate(projectDTOToReturn.getCompletionDate())
                    .status(projectDTOToReturn.getStatus())
                    .priority(projectDTOToReturn.getPriority())
                    .listOfTasks(projectDTOToReturn.getListOfTasks())
                    .build();
            projectRepository.save(resultProject);

            return null;
        }
        return null;
    }

    public Project viewProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }
}
