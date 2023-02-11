package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Service layer uses the @Repository
 */
@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findAllByTaskId(Long taskId);

    NoteEntity findNoteEntityByTaskIdAndId(Long taskId,Long notesId);
}
