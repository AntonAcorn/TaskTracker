package ru.acorn.taskTracker.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.taskTracker.dto.TaskDTO;
import ru.acorn.taskTracker.entity.Task;
import ru.acorn.taskTracker.service.TaskService;
import ru.acorn.taskTracker.utils.ErrorUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@Log4j
public class TaskController {
   private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @RequestMapping("/create")
    public HttpEntity<?> createTask (@RequestBody @Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult);
            ErrorUtils.returnError(bindingResult);
        }
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
