package com.fastcampus.fastcampusspringsecurity.service;

import com.fastcampus.fastcampusspringsecurity.domain.User;
import com.fastcampus.fastcampusspringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    // Username 으로 User 찾기
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
