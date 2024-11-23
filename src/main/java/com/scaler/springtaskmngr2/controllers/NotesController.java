package com.scaler.springtaskmngr2.controllers;

import com.scaler.springtaskmngr2.dto.ErrorResponse;
import com.scaler.springtaskmngr2.entities.NoteEntity;
import com.scaler.springtaskmngr2.entities.TaskEntity;
import com.scaler.springtaskmngr2.services.NoteServiceImpl;
import com.scaler.springtaskmngr2.services.NotesService;
import com.scaler.springtaskmngr2.services.TasksService;
import com.scaler.springtaskmngr2.services.TasksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class NotesController {

    NotesService notesService;
    TasksService tasksService;

    public NotesController(NotesService notesService, TasksService tasksService) {
        this.tasksService = tasksService;
        this.notesService = notesService;
    }

    @GetMapping(path = "/tasks/{id}/notes")
    public ResponseEntity<List<NoteEntity>> getNotesByTask(@PathVariable("id") Integer taskId) {
        List<NoteEntity> notes = notesService.getNotesByTaskId(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping(path = "/tasks/{id}/notes")
    public ResponseEntity<NoteEntity> createNoteInTask(@PathVariable("id") Integer taskId, @RequestBody NoteEntity note) {
        TaskEntity dbTask = tasksService.getTask(taskId);
        NoteEntity createdNote = notesService.createNoteInTask(dbTask, note);
        return ResponseEntity.created(URI.create("/tasks/"+ taskId + "/notes/"+createdNote.getId())).body(createdNote);
    }

    @DeleteMapping(path = "/tasks/{id}/notes/{noteId}")
    public ResponseEntity<NoteEntity> deleteNoteFromTask(@PathVariable("id") Integer taskId,
                                                          @PathVariable("noteId") Integer noteId) {
        NoteEntity deletedNote = notesService.deleteNoteInTask(taskId, noteId);
        return ResponseEntity.accepted().body(deletedNote);
    }

    @ExceptionHandler({TasksServiceImpl.TaskNotFoundException.class,
            NoteServiceImpl.NoteNotFoundException.class})
    public ResponseEntity<ErrorResponse> ErrorHandler(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
