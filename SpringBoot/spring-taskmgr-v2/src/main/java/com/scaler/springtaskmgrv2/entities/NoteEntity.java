package com.scaler.springtaskmgrv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity name is in plural because physical Table will have many entries(rows) but Class name should be singular as entails how single node is saved
 */
@Entity(name = "notes")
public class NoteEntity extends BaseEntity {

    @Column(name = "body" , nullable = false , length = 500)
    String body;
}
