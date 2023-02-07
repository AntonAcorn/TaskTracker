package ru.acorn.taskTracker.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acorn.taskTracker.dto.TaskDTO;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
   private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @RequestMapping("/create")
    public HttpEntity<?> createTask (@RequestBody Task task){
        taskService.createTask(task);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public TaskDTO viewTaskById (@PathVariable Long id){
        return taskService.viewTask(id);
    }

    @PatchMapping("/edit/{id}")
    public TaskDTO editTaskById(@PathVariable Long id, @RequestBody TaskDTO updatedTaskToSave){
        return taskService.editTask(id, updatedTaskToSave);
    }

    @GetMapping()
    public List<Task> viewAllTasks(){
        return taskService.viewAllTasks();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
}
