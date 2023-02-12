package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.dtos.ErrorResponse;
import com.scaler.springtaskmgrv2.services.NotesService;
import com.scaler.springtaskmgrv2.services.TasksService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleTaskNotFoundException(TasksService.TaskNotFoundException e) {
        log.error(e.getStackTrace());
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotesService.NoteNotFoundException.class)
    ResponseEntity<ErrorResponse> handleNoteNotFoundException(NotesService.NoteNotFoundException e) {
        log.error(e.getStackTrace());
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
