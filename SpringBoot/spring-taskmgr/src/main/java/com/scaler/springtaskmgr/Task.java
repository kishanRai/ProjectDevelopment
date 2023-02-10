package com.scaler.springtaskmgr;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    Integer id;
    String title;
    String description;
    Date dueDate;
    LocalDate startDate;

    public Task(Integer id, String title, String description, Date dueDate, LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    public Task() {

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
