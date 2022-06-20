package com.fastcampus.fastcampusspringsecurity.controller;

import com.fastcampus.fastcampusspringsecurity.domain.Note;
import com.fastcampus.fastcampusspringsecurity.domain.User;
import com.fastcampus.fastcampusspringsecurity.dto.NoteRegisterDto;
import com.fastcampus.fastcampusspringsecurity.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    // 노트 조회
    @GetMapping
    public ModelAndView getNote(Authentication authentication) {
        List<Note> notes = noteService.getNote((User) authentication.getPrincipal());

        return new ModelAndView(
                "note/index",
                Map.of("notes", notes)
        );
    }

    // 노트 저장
    @PostMapping
    public String saveNote(
            Authentication authentication,
            @ModelAttribute NoteRegisterDto noteRegisterDto
    ) {
        noteService.saveNote(
                (User) authentication.getPrincipal(),
                noteRegisterDto.getTitle(),
                noteRegisterDto.getContent()
        );

        // 저장 후 노트 페이지로
        return "redirect:note";
    }

    // 노트 삭제
    @DeleteMapping
    public String deleteNote(
            Authentication authentication,
            @RequestParam Long id
    ) {
        noteService.deleteNote((User) authentication.getPrincipal(), id);

        // 삭제 후 노트 페이지
        return "redirect:note";
    }
}
