package com.fastcampus.fastcampusspringsecurity.repository;

import com.fastcampus.fastcampusspringsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
