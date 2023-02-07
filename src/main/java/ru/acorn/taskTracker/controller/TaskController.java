package ru.acorn.taskTracker.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.service.TaskService;

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
}
