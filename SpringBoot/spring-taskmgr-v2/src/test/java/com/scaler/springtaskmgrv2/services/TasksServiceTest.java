package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TasksServiceTest {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    NotesRepository notesRepository;


    @Test
    void getAllTasksEntity() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        List<TaskEntity> taskEntities = tasksService.getAllTasksEntity();

        Assertions.assertNotNull(taskEntities);
    }

    @Test
    void getTaskEntityByID() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        TaskEntity fetchTaskEntity = tasksService.getTaskEntityByID(taskEntity.getId());

        Assertions.assertNotNull(fetchTaskEntity);
    }

    @Test
    void createTaskEntity() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());

        Assertions.assertNotNull(taskEntity);
    }

    @Test
    void updateTaskEntity() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        TaskEntity updatedTaskEntity = tasksService.updateTaskEntity(taskEntity.getId(), "TEST UPDATED TASK-ENTITY", null, Boolean.TRUE, LocalDate.MAX);

        Assertions.assertNotNull(updatedTaskEntity);
    }

    @Test
    void deleteTaskEntity() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        TaskEntity secondTaskEntity = tasksService.createTaskEntity("TEST SECOND TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        TaskEntity deleteTaskEntity = tasksService.deleteTaskEntity(secondTaskEntity.getId());

        Assertions.assertNotNull(deleteTaskEntity);
        Assertions.assertEquals(1, tasksRepository.findAll().size());
    }

    @Test
    void getAllTaskEntitiesWithTitle() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.FALSE, LocalDate.now());
        List<TaskEntity> fetchTaskEntity = tasksService.getAllTaskEntitiesWithTitle(taskEntity.getTitle());

        Assertions.assertNotNull(fetchTaskEntity);
    }

    @Test
    void getAllCompletedTaskEntities() {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST TASK-ENTITY", "TEST DESCRIPTION", Boolean.TRUE, LocalDate.now());
        List<TaskEntity> fetchTaskEntity = tasksService.getAllCompletedTaskEntities();

        Assertions.assertNotNull(fetchTaskEntity);
    }
}