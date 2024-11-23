package com.scaler.springtaskmngr2.controllers;

import com.scaler.springtaskmngr2.dto.ErrorResponse;
import com.scaler.springtaskmngr2.entities.TaskEntity;
import com.scaler.springtaskmngr2.services.TasksService;
import com.scaler.springtaskmngr2.services.TasksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TasksController {

    TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<TaskEntity>> getTasks(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) Boolean completed) {
        List<TaskEntity> tasks = null;
        if(title != null) {
           tasks = tasksService.getTasksByTitle(title);
        } else if (completed != null) {
            tasks = tasksService.getTasksByCompleteStatus(completed);
        } else {
            tasks = tasksService.getTasks();
        }
        return ResponseEntity.ok(tasks);
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        TaskEntity savedTask = tasksService.createTask(task);
        return ResponseEntity.created(URI.create("/tasks/"+savedTask.getId())).body(savedTask);
    }

    @GetMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable("id") Integer id) {
        TaskEntity task = tasksService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody TaskEntity task) {
        TaskEntity updatedTask = tasksService.updateTask(task, id);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") Integer id) {
        TaskEntity deletedTask = tasksService.deleteTask(id);
        return ResponseEntity.accepted().body(deletedTask);
    }

    @ExceptionHandler(TasksServiceImpl.TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> ErrorHandler(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
