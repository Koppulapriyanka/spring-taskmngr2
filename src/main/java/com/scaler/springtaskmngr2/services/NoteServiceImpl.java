package com.scaler.springtaskmngr2.services;

import com.scaler.springtaskmngr2.entities.NoteEntity;
import com.scaler.springtaskmngr2.entities.TaskEntity;
import com.scaler.springtaskmngr2.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NotesService{

    NotesRepository notesRepository;

    public NoteServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public static class NoteNotFoundException extends IllegalArgumentException {
        public NoteNotFoundException(Integer noteId) {
            super("Note with id: "+ noteId +" not found");
        }
    }

    @Override
    public List<NoteEntity> getNotesByTaskId(Integer taskId) {
        return notesRepository.findAllByTaskId(taskId);
    }

    @Override
    public NoteEntity createNoteInTask(TaskEntity dbTask, NoteEntity note) {
        note.setTask(dbTask);
        return notesRepository.save(note);
    }

    @Override
    public NoteEntity deleteNoteInTask(Integer taskId, Integer noteId) {
        NoteEntity dbNote = getNote(noteId);
        notesRepository.delete(dbNote);
        return dbNote;
    }

    @Override
    public NoteEntity getNote(Integer noteId) {
        return notesRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException(noteId));
    }
}
