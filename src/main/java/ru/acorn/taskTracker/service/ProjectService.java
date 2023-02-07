package ru.acorn.taskTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.exception.ProjectNotFoundException;
import ru.acorn.taskTracker.repository.ProjectRepository;
import ru.acorn.taskTracker.repository.TaskRepository;
import ru.acorn.taskTracker.utils.ModelMapperUtil;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ModelMapperUtil modelMapperUtil;

    public ProjectService(ProjectRepository projectRepository,
                          TaskRepository taskRepository,
                          ModelMapperUtil modelMapperUtil) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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
            throw new ProjectNotFoundException();
        }
    }

    @Transactional
    public ProjectDTO editProject(Long id, ProjectDTO projectDTOToReturn) {
        var projectToBeFoundById = projectRepository.findById(id);
        if (projectToBeFoundById.isPresent()){
            var projectResult = projectToBeFoundById.get();
            projectResult = Project.builder()
                    .id(projectResult.getId())
                    .name(projectDTOToReturn.getName())
                    .startTimeOfProject(projectResult.getStartTimeOfProject())
                    .completionDate(projectDTOToReturn.getCompletionDate())
                    .status(projectDTOToReturn.getStatus())
                    .priority(projectDTOToReturn.getPriority())
                    .listOfTasks(projectDTOToReturn.getListOfTasks())
                    .build();
            projectRepository.save(projectResult);
            return modelMapperUtil.projectConvertToProjectDTO(projectResult);
        }else{
            throw new ProjectNotFoundException();
        }
    }

    public List<Project> viewAllProjects(){
        return projectRepository.findAll();
    }

    public List<Task> viewAllTasksOfProjectById(Long id){
       return projectRepository.findById(id).map(Project::getListOfTasks).orElseThrow(ProjectNotFoundException::new);
    }

    @Transactional
    public void deleteProjectById(Long id){
        projectRepository.deleteById(id);
    }
}
