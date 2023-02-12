package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TasksService {
    TasksRepository tasksRepository;
    NotesRepository notesRepository;

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    /**
     * Fetches all Task  Entities
     *
     * @return
     */
    public List<TaskEntity> getAllTasksEntity() {
        return tasksRepository.findAll();
    }

    /**
     * Fetches TaskEntity by ID
     *
     * @param id
     * @return
     */
    public TaskEntity getTaskEntityByID(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Creates a new TaskEntity
     *
     * @param title
     * @param description
     * @param dueDate
     * @return
     */
    public TaskEntity createTaskEntity(String title, String description, Boolean completed, LocalDate dueDate) {
        var newTaskEntity = new TaskEntity();
        return persistTask(title, description, completed, dueDate, newTaskEntity);
    }


    /**
     * Updates a TaskEntity
     *
     * @param id
     * @param title
     * @param description
     * @param dueDate
     * @return
     */
    public TaskEntity updateTaskEntity(Long id, String title, String description, Boolean completed, LocalDate dueDate) {
        var taskTobeUpdated = getTaskEntityByID(id);
        return persistTask(title, description, completed, dueDate, taskTobeUpdated);
    }

    private TaskEntity persistTask(String title, String description, Boolean completed, LocalDate dueDate, TaskEntity taskTobeUpdated) {
        if (null != title) taskTobeUpdated.setTitle(title);
        if (null != description) taskTobeUpdated.setDescription(description);
        if (null != completed) taskTobeUpdated.setCompleted(completed);
        if (null != dueDate) taskTobeUpdated.setDueDate(dueDate);
        tasksRepository.save(taskTobeUpdated);
        return taskTobeUpdated;
    }

    /**
     * Deletes a TaskEntity
     *
     * @param id
     * @return
     */
    public TaskEntity deleteTaskEntity(Long id) {
        var taskTobeDeleted = getTaskEntityByID(id);
        if (null != taskTobeDeleted) tasksRepository.deleteById(id);
        return taskTobeDeleted;
    }

    /**
     * Returns all tasks with the given title
     *
     * @param title
     * @return
     */
    public List<TaskEntity> getAllTaskEntitiesWithTitle(String title) {
        return tasksRepository.findAllByTitle(title);
    }

    /**
     * Returns all tasks with given completed status
     *
     * @return
     */
    public List<TaskEntity> getAllCompletedTaskEntities() {
        return tasksRepository.findAllByCompleted(true);
    }


    public class TaskNotFoundException extends NoSuchElementException {
        public TaskNotFoundException(Long id) {
            super("TaskEntity with id :" + id + " not found !!!");
        }
    }
}
