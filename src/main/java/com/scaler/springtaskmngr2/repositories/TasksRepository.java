package com.scaler.springtaskmngr2.repositories;

import com.scaler.springtaskmngr2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findByTitle(String title);

    List<TaskEntity> findByCompleted(Boolean completed);
}
