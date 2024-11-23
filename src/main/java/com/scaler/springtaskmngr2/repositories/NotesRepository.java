package com.scaler.springtaskmngr2.repositories;

import com.scaler.springtaskmngr2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {

   // @Query(value = "select * from Notes where taskId = ?", nativeQuery = true)
    List<NoteEntity> findAllByTaskId(Integer taskId);
}
