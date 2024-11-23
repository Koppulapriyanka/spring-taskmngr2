package com.scaler.springtaskmngr2.services;

import com.scaler.springtaskmngr2.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TasksService {

    List<TaskEntity> getTasks();
    TaskEntity createTask(TaskEntity task);
    TaskEntity getTask(Integer id);
    TaskEntity updateTask(TaskEntity task, Integer id);
    TaskEntity deleteTask(Integer id);
    List<TaskEntity> getTasksByTitle(String title);

    List<TaskEntity> getTasksByCompleteStatus(Boolean completed);
    
}
