package ru.acorn.taskTracker.service;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.dto.TaskDTO;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.exception.EntityNotFoundException;
import ru.acorn.taskTracker.repository.ProjectRepository;
import ru.acorn.taskTracker.repository.TaskRepository;
import ru.acorn.taskTracker.utils.ModelMapperUtil;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Log4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapperUtil modelMapperUtil;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       ModelMapperUtil modelMapperUtil) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Transactional
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public TaskDTO viewTask(Long id) {
        var taskToBeFound = taskRepository.findById(id);
        if (taskToBeFound.isPresent()) {
            var resultTask = taskToBeFound.get();
            return modelMapperUtil.taskConvertToTaskDTO(resultTask);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Task> viewAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional
    public TaskDTO editTask(Long id, TaskDTO taskDTOToReturn) {
        var taskToBeFoundById = taskRepository.findById(id);
        if (taskToBeFoundById.isPresent()){
            var taskResult = taskToBeFoundById.get();
            taskResult = Task.builder()
                    .id(taskResult.getId())
                    .taskName(taskDTOToReturn.getTaskName())
                    .taskStatus(taskDTOToReturn.getTaskStatus())
                    .taskDescription(taskDTOToReturn.getTaskDescription())
                    .priority(taskDTOToReturn.getPriority())
                    .project(taskDTOToReturn.getProject())
                    .build();
            taskRepository.save(taskResult);
            return modelMapperUtil.taskConvertToTaskDTO(taskResult);
        }else{
            log.error("There is no such task");
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
}