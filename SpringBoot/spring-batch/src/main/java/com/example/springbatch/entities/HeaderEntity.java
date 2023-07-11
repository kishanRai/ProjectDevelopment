package com.example.springbatch.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "headers")
@Getter
@Setter
public class HeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "invoiceNumber", nullable = false, length = 50)
    UUID invoiceNumber;

    @Column(name = "sourceBackend", nullable = true, length = 100)
    String sourceBackend;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "issueDate", nullable = false, updatable = false)
    LocalDateTime issueDate;

    @Column(name = "invoiceTypeCode", nullable = true, length = 10)
    String invoiceTypeCode;

    @Column(name = "invoiceCurrencyCode", nullable = true, length = 3)
    String invoiceCurrencyCode;
}
