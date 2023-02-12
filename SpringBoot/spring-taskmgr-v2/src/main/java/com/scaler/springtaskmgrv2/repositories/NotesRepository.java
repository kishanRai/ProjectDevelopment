package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Service layer uses the @Repository
 */
@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findAllByTaskId(Long taskId);

    Optional<NoteEntity> findNoteEntityByTaskIdAndId(Long taskId, Long notesId);
}
