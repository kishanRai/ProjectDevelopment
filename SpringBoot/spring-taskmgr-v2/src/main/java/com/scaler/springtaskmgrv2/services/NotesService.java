package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotesService {

    TasksRepository tasksRepository;
    NotesRepository notesRepository;

    public NotesService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    /**
     * Returns all NotesEntity for a task with the given TaskEntity ID
     *
     * @param id
     * @return
     */
    public List<NoteEntity> getAllNotesLinkedTaskEntity(Long id) {
        return notesRepository.findAllByTaskId(id);
    }

    /**
     * Fetch specific NoteEntity by ID
     *
     * @param notesId
     * @return
     */
    public NoteEntity getSpecificNoteEntity(Long id, Long notesId) {
        return notesRepository.findNoteEntityByTaskIdAndId(id, notesId).orElseThrow(() -> new NoteNotFoundException(notesId,id));
    }

    /**
     * Creates a new NoteEntity for a task with the given TaskEntityID
     *
     * @param id
     * @param body
     * @return
     */
    public NoteEntity createNoteEntityWithTaskEntityID(Long id, String body) {
        TaskEntity taskEntity = tasksRepository.findById(id).orElseThrow();
        var noteEntity = new NoteEntity();
        noteEntity.setTask(taskEntity);
        noteEntity.setBody(body);
        notesRepository.save(noteEntity);
        return noteEntity;
    }

    /**
     * Deletes a NoteEntity with the given ID for a Task and NoteEntity
     *
     * @param id
     * @return
     */
    public NoteEntity deleteNoteEntityWithIDAndTaskID(Long notesID, Long id) {
        NoteEntity noteEntity = notesRepository.findNoteEntityByTaskIdAndId(id, notesID).get();
        notesRepository.delete(noteEntity);
        return noteEntity;
    }

    public class NoteNotFoundException extends NoSuchElementException {
        public NoteNotFoundException(Long notesId, Long taskId) {
            super("NoteEntity with id :" + notesId + "  linked to Task Entity :" +taskId+" not found !!!");
        }
    }
}
