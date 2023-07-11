package com.example.springbatch.repositories;

import com.example.springbatch.entities.HeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<HeaderEntity, String> {
}
