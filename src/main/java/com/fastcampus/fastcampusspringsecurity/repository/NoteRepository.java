package com.fastcampus.fastcampusspringsecurity.repository;

import com.fastcampus.fastcampusspringsecurity.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
