package ru.acorn.taskTracker.service;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.exception.AppEntityNotFoundException;
import ru.acorn.taskTracker.repository.ProjectRepository;
import ru.acorn.taskTracker.utils.ModelMapperUtil;

import java.util.List;

@Service
@Log4j
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapperUtil modelMapperUtil;

    public ProjectService(ProjectRepository projectRepository,
                          ModelMapperUtil modelMapperUtil
                          ) {
        this.projectRepository = projectRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Transactional
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    public ProjectDTO viewProject(Long id) {
        var projectToBeFound = projectRepository.findById(id);
        if (projectToBeFound.isPresent()) {
            var resultProject = projectToBeFound.get();
            return modelMapperUtil.projectConvertToProjectDTO(resultProject);
        } else {
            log.error("There is no such project");
            throw new AppEntityNotFoundException();
        }
    }

    @Transactional
    public ProjectDTO editProject(Long id, ProjectDTO projectDTOToReturn) {
        var projectToBeFoundById = projectRepository.findById(id);
        if (projectToBeFoundById.isPresent()) {
            var projectResult = projectToBeFoundById.get();
            projectResult = Project.builder()
                    .id(projectResult.getId())
                    .projectName(projectDTOToReturn.getProjectName())
                    .startTimeOfProject(projectResult.getStartTimeOfProject())
                    .completionDate(projectDTOToReturn.getCompletionDate())
                    .status(projectDTOToReturn.getStatus())
                    .priority(projectDTOToReturn.getPriority())
                    .listOfTasks(projectDTOToReturn.getListOfTasks())
                    .build();
            projectRepository.save(projectResult);
            return modelMapperUtil.projectConvertToProjectDTO(projectResult);
        } else {
            log.error("There is no such project");
            throw new AppEntityNotFoundException();
        }
    }

    public List<Project> viewAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> viewAllProjectsStartAt(String startAt) {
        return projectRepository.findAllByProjectNameStartsWith(startAt);
    }

    public List<Project> viewAllProjectsEndWith(String endsWith) {
        return projectRepository.findAllByProjectNameEndsWith(endsWith);
    }

    public List<Project> viewAllByOrderByPriority() {
        return projectRepository.findAllByOrderByPriority();
    }

    public List<Project> findAllByOrderByStartTimeOfProject() {
        return projectRepository.findAllByOrderByStartTimeOfProject();
    }

    public List<Task> viewAllTasksOfProjectById(Long id) {
        return projectRepository.findById(id).map(Project::getListOfTasks).orElseThrow(AppEntityNotFoundException::new);
    }

    @Transactional
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
