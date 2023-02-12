package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.services.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/tasksV2/{id}/notes")
@RestController
public class NotesController {

    NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    /**
     * Gets all the Notes by TaskEntity's ID
     *
     * @param id
     * @return
     */
    @GetMapping
    ResponseEntity<List<NoteEntity>> getAllNotesWithTaskEntityID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notesService.getAllNotesLinkedTaskEntity(id));
    }

    /**
     * Fetches particular NoteEntity of provided TaskEntity ID
     *
     * @param id
     * @param notesId
     * @return
     */
    @GetMapping("{notesId}")
    ResponseEntity<NoteEntity> getSpecificNoteEntity(@PathVariable("id") Long id, @PathVariable("notesId") Long notesId) {
        return ResponseEntity.ok(notesService.getSpecificNoteEntity(id, notesId));
    }


    /**
     * Creates a NoteEntity
     *
     * @param id
     * @param noteEntity
     * @return
     */
    @PostMapping("/createNote")
    ResponseEntity<NoteEntity> createNoteEntity(@PathVariable("id") Long id, @RequestBody NoteEntity noteEntity) {
        var newNoteEntity = notesService.createNoteEntityWithTaskEntityID(id, noteEntity.getBody());
        return ResponseEntity.created(URI.create("/tasksV2/id/notes/" + newNoteEntity.getId())).body(newNoteEntity);
    }

    /**
     * Deletes a NoteEntity of given TaskEntity ID
     *
     * @param id
     * @param notesID
     * @return
     */
    @DeleteMapping("{notesId}")
    ResponseEntity<NoteEntity> deleteNoteEntityWithTaskEntityID(@PathVariable("id") Long id, @PathVariable("notesId") Long notesID) {
        return ResponseEntity.accepted().body(notesService.deleteNoteEntityWithIDAndTaskID(notesID, id));
    }
}
