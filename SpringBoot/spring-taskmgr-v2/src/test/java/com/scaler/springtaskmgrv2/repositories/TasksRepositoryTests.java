package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TasksRepositoryTests {
    @Autowired
    TasksRepository tasksRepository;

    @Test
    void testCreateTask() {
        TaskEntity task = new TaskEntity();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        var savedTask = tasksRepository.save(task);
        Assertions.assertNotNull(savedTask);
    }

    @Test
    void readTasksWorks() {
        TaskEntity taskFirst = new TaskEntity();
        taskFirst.setTitle("Test Task First");
        taskFirst.setDescription("Test Description");
        taskFirst.setCompleted(false);

        TaskEntity taskSecond = new TaskEntity();
        taskSecond.setTitle("Test Task Second");
        taskSecond.setDescription("Test Description");
        taskSecond.setCompleted(false);

        tasksRepository.save(taskFirst);
        tasksRepository.save(taskSecond);

        var tasks = tasksRepository.findAll();
        Assertions.assertNotNull(tasks);
        Assertions.assertEquals(2, tasks.size());

    }

    @Test
    void findByCompletedWorks() {
        TaskEntity taskFirst = new TaskEntity();
        taskFirst.setTitle("Test Task First");
        taskFirst.setDescription("Test Description");
        taskFirst.setCompleted(true);

        TaskEntity taskSecond = new TaskEntity();
        taskSecond.setTitle("Test Task Second");
        taskSecond.setDescription("Test Description");
        taskSecond.setCompleted(false);

        tasksRepository.save(taskFirst);
        tasksRepository.save(taskSecond);

        var completedTasks = tasksRepository.findAllByCompleted(true);
        var inCompletedTasks = tasksRepository.findAllByCompleted(false);

        Assertions.assertEquals(1, completedTasks.size());
        Assertions.assertEquals(1, inCompletedTasks.size());
    }
}
