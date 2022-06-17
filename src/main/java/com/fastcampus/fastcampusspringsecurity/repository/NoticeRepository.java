package com.fastcampus.fastcampusspringsecurity.repository;

import com.fastcampus.fastcampusspringsecurity.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
