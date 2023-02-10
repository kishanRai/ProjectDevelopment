package com.scaler.springtaskmgr.services;

import com.scaler.springtaskmgr.entities.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TasksService {
    private final List<Task> taskList;

    /**
     * To handle Multi Thread problem related to next TaskId generation
     */
    private AtomicInteger taskId = new AtomicInteger(1);

    public TasksService() {
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.getAndIncrement(), "Task Default First", "Already Created Task", new Date(), LocalDate.now()));
    }

    public static class TaskNotFoundException extends IllegalStateException {
        public TaskNotFoundException(Integer id) {
            super("Task with id" + id + "not found");
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public Task createTask(String title, String description, Date dueDate, LocalDate startDate) {
        var newTask = new Task(taskId.getAndIncrement(), title, description, dueDate, startDate);
        taskList.add(newTask);
        return newTask;
    }

    public Task getTaskById(Integer id) {
        return taskList.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, String title, String description, Date dueDate, LocalDate startDate) {
        var task = getTaskById(id);
        if (null != title) task.setTitle(title);
        if (null != description) task.setDescription(description);
        if (null != dueDate) task.setDueDate(dueDate);
        if (null != startDate) task.setStartDate(startDate);
        return task;
    }

    public Task deleteTask(Integer id) {
        var task = getTaskById(id);
        taskList.remove(task);
        return task;
    }
}
