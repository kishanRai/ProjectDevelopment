package com.scaler.springtaskmgrv2.entities;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class is deliberately created abstract so that object cannot be created and Used @MappedSuperclass so that ORM will not create Table
 */
@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * TODO : @CreatedDate is used with SQL and NOSQL Database. @CreationTimestamp with NOSQL database only
     * Need to refactor and use the one
     */

    @CreationTimestamp
    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;
}
