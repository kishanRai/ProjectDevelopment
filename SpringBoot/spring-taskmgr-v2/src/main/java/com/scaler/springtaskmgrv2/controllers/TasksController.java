package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasksV2")
public class TasksController {
    TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    ResponseEntity<List<TaskEntity>> getTaskEntities() {
        return ResponseEntity.ok(tasksService.getAllTasksEntity());
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskEntity> getTaskEntityByID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tasksService.getTaskEntityByID(id));
    }

    @PostMapping("/createTaskEntity")
    ResponseEntity<TaskEntity> createTaskEntity(@RequestBody TaskEntity taskEntity) {
        var newTaskEntity = tasksService.createTaskEntity(taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getCompleted(), taskEntity.getDueDate());
        return ResponseEntity.created(URI.create("/tasksV2/" + newTaskEntity.getId())).body(newTaskEntity);
    }

    @PatchMapping("/{id}")
    ResponseEntity<TaskEntity> updateTaskEntity(@PathVariable("id") Long id, @RequestBody TaskEntity taskEntity) {
        var updateTaskEntity = tasksService.updateTaskEntity(id, taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getCompleted(),taskEntity.getDueDate());
        return ResponseEntity.accepted().body(updateTaskEntity);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TaskEntity> deleteTaskEntity(@PathVariable("id") Long id) {
        return ResponseEntity.accepted().body(tasksService.deleteTaskEntity(id));
    }
}
