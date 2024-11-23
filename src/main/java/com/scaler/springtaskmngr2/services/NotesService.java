package com.scaler.springtaskmngr2.services;

import com.scaler.springtaskmngr2.entities.NoteEntity;
import com.scaler.springtaskmngr2.entities.TaskEntity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface NotesService {

    List<NoteEntity> getNotesByTaskId(Integer taskId);

    NoteEntity createNoteInTask(TaskEntity taskEntity, NoteEntity note);

    NoteEntity deleteNoteInTask(Integer taskId, Integer noteId);

    NoteEntity getNote(Integer noteId);
}
