package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.services.NotesService;
import com.scaler.springtaskmgrv2.services.TasksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NotesRepositoryTest {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    NotesRepository notesRepository;



    @Test
    void findAllByTaskId() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        NoteEntity noteEntity = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        List<NoteEntity> listNoteEntities  = notesService.getAllNotesLinkedTaskEntity(noteEntity.getId());

        Assertions.assertNotNull(taskEntity);
        Assertions.assertNotNull(noteEntity);
        Assertions.assertNotNull(listNoteEntities);
    }

    @Test
    void findNoteEntityByTaskIdAndId() {
        TasksService tasksService = new TasksService(tasksRepository,notesRepository);
        TaskEntity taskEntity = tasksService.createTaskEntity("TEST","TEST TASKENTITY" , Boolean.FALSE, LocalDate.now());
        NotesService notesService = new NotesService(tasksRepository,notesRepository);
        NoteEntity noteEntity = notesService.createNoteEntityWithTaskEntityID(1L,"First Notes Entries");
        NoteEntity findNoteEntity = notesRepository.findNoteEntityByTaskIdAndId(taskEntity.getId(),noteEntity.getId());
        Assertions.assertNotNull(findNoteEntity);

    }
}