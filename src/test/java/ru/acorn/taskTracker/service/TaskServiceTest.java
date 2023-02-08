package ru.acorn.taskTracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.repository.ProjectRepository;
import ru.acorn.taskTracker.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.acorn.taskTracker.entity.enums.ProjectStatus.Active;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask() {
        var task1 = getOneMockTask();
        taskService.createTask(task1);
        Mockito.verify(taskRepository).save(task1);
    }

    @Test
    void viewTask() {
        var task1 = getOneMockTask();
        Long id = 1L;
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.ofNullable(task1));
        taskRepository.findById(id);
        Mockito.verify(taskRepository).findById(id);
    }

    @Test
    void viewAllTasks() {
        var listOfTasks = getTasks();
        Mockito.when(taskRepository.findAll()).thenReturn(listOfTasks);
        var result = taskService.viewAllTasks();
        assertNotNull(result);
        assertEquals(listOfTasks, result);
    }

    @Test
    void deleteTaskById() {
        var id = 1L;
        taskService.deleteTaskById(id);
        Mockito.verify(taskRepository).deleteById(id);
    }

    private List<Task> getTasks(){
        var task1 = Task.builder()
                .id(1L)
                .taskName("Test")
                .project(new Project())
                .taskStatus(null)
                .build();

        var task2  = Task.builder()
                .id(1L)
                .taskName("Test2")
                .project(new Project())
                .taskStatus(null)
                .build();
        return List.of(task1, task2);
    }

    private Task getOneMockTask(){
        return Task.builder()
                .id(1L)
                .taskName("Test")
                .project(new Project())
                .taskStatus(null)
                .build();
    }
}