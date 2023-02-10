package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.dtos.ErrorResponse;
import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.services.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskController {
    private TasksService tasksService;


    /**
     * Default Constructor
     */
    public TaskController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    /**
     * @return Fetches Existing List of Task
     */
    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(tasksService.getTasks());
    }

    /**
     * Create a new task
     *
     * @param task
     * @return Task
     */
    @PostMapping("/createTask")
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        var newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getDueDate(), task.getStartDate());
        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    /**
     * Fetch single task
     *
     * @param id
     * @return
     */
    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getSingleTask(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tasksService.getTaskById(id));
    }

    /**
     * Delete a single task
     *
     * @param id
     * @return
     */
    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) {
        return ResponseEntity.accepted().body(tasksService.deleteTask(id));
    }

    /**
     * Update a single task
     *
     * @param id
     * @param task
     * @return
     */
    @PatchMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        var updatedTask = tasksService.updateTask(id, task.getTitle(), task.getDescription(), task.getDueDate(), task.getStartDate());
        return ResponseEntity.accepted().body(updatedTask);
    }

    ResponseEntity<ErrorResponse> handleErrors(TasksService.TaskNotFoundException taskNotFoundException) {
        return new ResponseEntity<>(
                new ErrorResponse(taskNotFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

}
