package com.fastcampus.fastcampusspringsecurity.service;

import com.fastcampus.fastcampusspringsecurity.domain.Notice;
import com.fastcampus.fastcampusspringsecurity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<Notice> getNotice() {
        return noticeRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Notice saveNotice(String title, String content) {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .build();

        return noticeRepository.save(notice);
    }

    @Transactional
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
