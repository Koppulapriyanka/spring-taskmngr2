package com.scaler.springtaskmngr2.services;

import com.scaler.springtaskmngr2.entities.TaskEntity;
import com.scaler.springtaskmngr2.repositories.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TasksServiceTests {

    @Autowired
    TasksRepository tasksRepository;
    TasksService tasksService;

    private TasksService getTasksService() {
        if(null == tasksService) {
            tasksService = new TasksServiceImpl(tasksRepository);
        }
        return tasksService;
    }

    @Test
    public void testCreateTask() {
        TaskEntity task = new TaskEntity();
        task.setTitle("springboot test");
        task.setDescription("springboot jpa test");
        task.setCompleted(false);
        task.setDueDate(new Date());
        TaskEntity savedTask = getTasksService().createTask(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testGetTasks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("springboot test");
        task1.setDescription("springboot jpa test");
        task1.setCompleted(false);
        task1.setDueDate(new Date());

        TaskEntity task2 = new TaskEntity();
        task2.setTitle("react");
        task2.setDescription("react learning");
        task2.setCompleted(false);
        task2.setDueDate(new Date());

        getTasksService().createTask(task1);
        getTasksService().createTask(task2);
        List<TaskEntity> tasks = getTasksService().getTasks();
        assertNotNull(tasks);
        assertEquals(2,tasks.size());
    }
}
