package com.scaler.springtaskmngr2.services;

import com.scaler.springtaskmngr2.entities.TaskEntity;
import com.scaler.springtaskmngr2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksServiceImpl implements TasksService{

    TasksRepository tasksRepository;

    public TasksServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public static class TaskNotFoundException extends IllegalArgumentException {
        public TaskNotFoundException(Integer id) {
            super("Task with id: "+ id +" not found");
        }
    }

    @Override
    public List<TaskEntity> getTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        return tasksRepository.save(task);
    }

    @Override
    public TaskEntity getTask(Integer id) {
        return tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public TaskEntity updateTask(TaskEntity task, Integer id) {
        TaskEntity dbTask = getTask(id);
        if(task.getDescription() != null) dbTask.setDescription(task.getDescription());
        if(task.getCompleted() != null) dbTask.setCompleted(task.getCompleted());
        if(task.getTitle() != null) dbTask.setTitle(task.getTitle());
        if(task.getDueDate() != null) dbTask.setDueDate(task.getDueDate());
        tasksRepository.save(dbTask);
        return dbTask;
    }

    @Override
    public TaskEntity deleteTask(Integer id) {
        TaskEntity dbTask = getTask(id);
        tasksRepository.delete(dbTask);
        return dbTask;
    }

    public List<TaskEntity> getTasksByTitle(String title) {
        return tasksRepository.findByTitle(title);
    }

    public List<TaskEntity> getTasksByCompleteStatus(Boolean completed) {
        return tasksRepository.findByCompleted(completed);
    }
}
