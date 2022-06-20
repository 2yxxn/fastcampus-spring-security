package com.fastcampus.fastcampusspringsecurity.service;

import com.fastcampus.fastcampusspringsecurity.domain.Note;
import com.fastcampus.fastcampusspringsecurity.domain.User;
import com.fastcampus.fastcampusspringsecurity.exception.GeneralException;
import com.fastcampus.fastcampusspringsecurity.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.fastcampus.fastcampusspringsecurity.constant.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    public List<Note> getNote(User user) {
        if (user == null) {
            throw new GeneralException(USER_NOT_FOUND);
        }

        // Admin 은 전체 노트를 출력
        // id 의 역순으로 출력
        if (user.isAdmin()) {
            return noteRepository
                    .findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        // User 가 작성한 Note 전부 출력
        return noteRepository.findByUserOrderByIdDesc(user);
    }

    @Transactional
    public Note saveNote(User user, String title, String content) {
        if (user == null) {
            throw new GeneralException(USER_NOT_FOUND);
        }

        Note note = Note.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();

        return noteRepository.save(note);
    }

    @Transactional
    public void deleteNote(User user, Long id) {
        if (user == null) {
            throw new GeneralException(USER_NOT_FOUND);
        }

        // User 가 작성한 Note 를 id 로 찾아오기
        Note note = noteRepository.findByIdAndUser(id, user);
        if (note != null) {
            noteRepository.delete(note);
        }
    }
}
