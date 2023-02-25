package com.scaler.blogapi.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRespository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    Optional<UserEntity> findById(Long id);
}
