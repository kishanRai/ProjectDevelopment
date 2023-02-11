package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NotesServiceTest {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    NotesRepository notesRepository;

    @Test
    void getAllNotesLinkedTaskEntity() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        NoteEntity noteEntity = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        List<NoteEntity> allNoteEntities = notesService.getAllNotesLinkedTaskEntity(noteEntity.getId());

        Assertions.assertNotNull(taskEntity);
        Assertions.assertNotNull(noteEntity);
        Assertions.assertNotNull(allNoteEntities);
    }

    @Test
    void getSpecificNoteEntity() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        NoteEntity noteEntity = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        NoteEntity specificNoteEntities = notesService.getSpecificNoteEntity(taskEntity.getId(),noteEntity.getId());

        Assertions.assertNotNull(taskEntity);
        Assertions.assertNotNull(noteEntity);
        Assertions.assertNotNull(specificNoteEntities);
    }

    @Test
    void createNoteEntityWithTaskEntityID() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        NoteEntity noteEntity = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        NoteEntity specificNoteEntities = notesService.createNoteEntityWithTaskEntityID(taskEntity.getId(),noteEntity.getBody());

        Assertions.assertNotNull(taskEntity);
        Assertions.assertNotNull(noteEntity);
        Assertions.assertNotNull(specificNoteEntities);
    }

    @Test
    void deleteNoteEntityWithIDAndTaskID() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        TaskEntity taskEntityFirst = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NoteEntity noteEntityFirst = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        NoteEntity noteEntitySecond = notesService.createNoteEntityWithTaskEntityID(1L,"Second Notes Entries");
        NoteEntity createFirstNoteEntity = notesService.createNoteEntityWithTaskEntityID(taskEntityFirst.getId(),noteEntityFirst.getBody());
        NoteEntity createSecondNoteEntity = notesService.createNoteEntityWithTaskEntityID(taskEntityFirst.getId(),noteEntitySecond.getBody());
        NoteEntity deleteSecondNoteEntity = notesService.deleteNoteEntityWithIDAndTaskID(noteEntitySecond.getId(),taskEntityFirst.getId());

        Assertions.assertNotNull(deleteSecondNoteEntity);
        Assertions.assertEquals(3,notesRepository.findAll().size());

    }
}