package ru.acorn.taskTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.taskTracker.dto.TaskDTO;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.exception.ProjectNotFoundException;
import ru.acorn.taskTracker.repository.TaskRepository;
import ru.acorn.taskTracker.utils.ModelMapperUtil;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapperUtil modelMapperUtil;

    public TaskService(TaskRepository taskRepository,
                       ModelMapperUtil modelMapperUtil) {
        this.taskRepository = taskRepository;
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
            throw new ProjectNotFoundException();
        }
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
            throw new ProjectNotFoundException();
        }
    }

    @Transactional
    public void deleteProjectById(Long id){
        taskRepository.deleteById(id);
    }
}