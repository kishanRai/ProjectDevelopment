package com.scaler.springtaskmgr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TaskController {
    List<Task> taskList;

    /**
     * To handle Multi Thread problem related to next TaskId generation
     */
    AtomicInteger taskId = new AtomicInteger(1);


    /**
     * Parameterized Constructor
     */

    /*public TaskController(List<Task> taskList) {
        this.taskList = taskList;
    }*/

    /**
     * Default Constructor
     */
    public TaskController() {
        Task firstTask = new Task();
        firstTask.setId(taskId.getAndIncrement());
        firstTask.setDescription("Task Controller First");
        firstTask.setDueDate(new Date());
        firstTask.setStartDate(LocalDate.now());

        Task secondTask = new Task();
        secondTask.setId(taskId.getAndIncrement());
        secondTask.setDescription("Task Controller Second");
        secondTask.setDueDate(new Date());
        secondTask.setStartDate(LocalDate.now());

        Task thirdTask = new Task();
        thirdTask.setId(taskId.getAndIncrement());
        thirdTask.setDescription("Task Controller third");
        thirdTask.setDueDate(new Date());
        thirdTask.setStartDate(LocalDate.now());

        List<Task> defaultList = new ArrayList<>();
        defaultList.add(firstTask);
        defaultList.add(secondTask);
        defaultList.add(thirdTask);

        taskList = defaultList;
    }

    /**
     * @return List of Task
     */
    @GetMapping("/tasks")
    List<Task> getTasks() {
        return taskList;
    }

    /**
     * @param task
     * @return Task
     */
    @PostMapping("/createTask")
    Task createTask(@RequestBody Task task) {
        var newTask = new Task(taskId.getAndIncrement(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getStartDate());
        taskList.add(newTask);
        return newTask;
    }
}
