package com.scaler.springtaskmgrv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity name is in plural because physical Table will have many entries(rows) but Class name should be singular as entails how single node is saved
 */
@Entity(name = "notes")
public class NoteEntity extends BaseEntity {

    @Column(name = "body", nullable = false, length = 500)
    String body;

    /**
     * Many to one because First Prefix is the class in which object added i.e. NoteEntity and second is the object on which it being added/applied
     * Relation will be many NOTES for single Task
     */
    @ManyToOne
    TaskEntity task;
}
