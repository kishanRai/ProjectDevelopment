package com.scaler.springtaskmgrv2.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity name is in plural because physical Table will have many entries(rows) but Class name should be singular as entails how single node is saved
 */

@Entity(name = "tasks")
public class TaskEntity extends BaseEntity {


    @Column(name = "title", nullable = false, length = 150)
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name = "due_Date", nullable = false)
    LocalDate dueDate;

}
