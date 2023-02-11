package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Service layer uses the @Repository
 */
@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

    /**
     * Method defination is created automatically by SimpleJpaRepository by giving name Pattern which it will create for
     *
     * @param completed
     * @return
     */
    List<TaskEntity> findAllByCompleted(boolean completed);

    /**
     * Ideally this is business logic terminology(i.e. 'overdue') so should not Repository Layer. Should be written on ServiceLayer
     * Repository layer supposed to not have business implementation.
     *
     * @return
     */
    @Query(value = "SELECT T FROM tasks T WHERE T.completed=FALSE AND T.dueDate<CURRENT_DATE ")
    List<TaskEntity> findAllOverDueTasks();

    @Query("SELECT T FROM tasks T WHERE T.title LIKE %?1%")
    List<TaskEntity> findAllByTitle(String title);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String tileFragment);

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, LocalDate dueDate)
}
