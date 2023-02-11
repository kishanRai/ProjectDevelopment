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

    @GetMapping
    ResponseEntity<List<NoteEntity>> getAllNotesWithTaskEntityID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notesService.getAllNotesLinkedTaskEntity(id));
    }

    @GetMapping("{notesId}")
    ResponseEntity<NoteEntity> getSpecificNoteEntity(@PathVariable("id") Long id,@PathVariable("notesId") Long notesId){
        return ResponseEntity.ok(notesService.getSpecificNoteEntity(id,notesId));
    }


    @PostMapping("/createNote")
    ResponseEntity<NoteEntity> createNoteEntity(@PathVariable("id") Long id, @RequestBody NoteEntity noteEntity) {
        var newNoteEntity = notesService.createNoteEntityWithTaskEntityID(id, noteEntity.getBody());
        return ResponseEntity.created(URI.create("/tasksV2/id/notes/" + newNoteEntity.getId())).body(newNoteEntity);
    }

    @DeleteMapping("{notesId}")
    ResponseEntity<NoteEntity> deleteNoteEntityWithTaskEntityID(@PathVariable("id") Long id,@PathVariable("notesId") Long notesID) {
        return ResponseEntity.accepted().body(notesService.deleteNoteEntityWithIDAndTaskID(notesID,id));
    }
}
