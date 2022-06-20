package com.fastcampus.fastcampusspringsecurity.repository;

import com.fastcampus.fastcampusspringsecurity.domain.Note;
import com.fastcampus.fastcampusspringsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // User 로 Note List 가져오는데, Id 의 역순으로
    List<Note> findByUserOrderByIdDesc(User user);
    Note findByIdAndUser(Long id, User user);
}
