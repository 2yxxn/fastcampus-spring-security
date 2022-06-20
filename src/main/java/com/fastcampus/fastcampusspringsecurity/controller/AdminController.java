package com.fastcampus.fastcampusspringsecurity.controller;

import com.fastcampus.fastcampusspringsecurity.domain.Note;
import com.fastcampus.fastcampusspringsecurity.domain.User;
import com.fastcampus.fastcampusspringsecurity.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final NoteService noteService;

    @GetMapping
    public ModelAndView getNoteForAdmin(Authentication authentication) {
        List<Note> notes = noteService.getNote((User) authentication.getPrincipal());

        return new ModelAndView(
                "admin/index",
                Map.of("notes", notes)
        );
    }
}
