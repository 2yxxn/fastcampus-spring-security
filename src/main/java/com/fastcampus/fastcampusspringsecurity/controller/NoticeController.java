package com.fastcampus.fastcampusspringsecurity.controller;

import com.fastcampus.fastcampusspringsecurity.domain.Notice;
import com.fastcampus.fastcampusspringsecurity.dto.NoteRegisterDto;
import com.fastcampus.fastcampusspringsecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    // 공지사항 조회
    @GetMapping
    public ModelAndView getNotice() {
        List<Notice> notices = noticeService.getNotice();

        return new ModelAndView(
                "notice/index",
                Map.of("notices", notices)
        );
    }

    // 관리자만
    // 공지사항 등록
    @PostMapping
    public String postNotice(
            @ModelAttribute NoteRegisterDto noteRegisterDto
    ) {
        noticeService.saveNotice(noteRegisterDto.getTitle(), noteRegisterDto.getContent());

        // 등록 후 notice page 로 돌아가기
        return "redirect:notice";
    }

    // 관리자만
    // 공지사항 삭제
    @DeleteMapping
    public String deleteNotice(@RequestParam Long id) {
        noticeService.deleteNotice(id);

        return "redirect:notice";
    }
}
