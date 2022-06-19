package com.fastcampus.fastcampusspringsecurity.service;

import com.fastcampus.fastcampusspringsecurity.domain.User;
import com.fastcampus.fastcampusspringsecurity.exception.GeneralException;
import com.fastcampus.fastcampusspringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fastcampus.fastcampusspringsecurity.constant.ErrorCode.ALREADY_REGISTERED_USER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // User 회원가입
    @Transactional
    public User signup(String username, String password) {
        // validation
        validateUserRegister(username);

        // username 이 존재하지 않을 경우
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_USER")
                .build();

        return userRepository.save(user);
    }

    // Admin 회원가입
    @Transactional
    public User signupAdmin(String username, String password) {
        // validation
        validateUserRegister(username);

        // username 이 존재하지 않을 경우
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_ADMIN")
                .build();

        return userRepository.save(user);
    }

    // Username 의 존재 여부
    private void validateUserRegister(String username) {
        if (userRepository.findByUsername(username) != null) {
            throw new GeneralException(ALREADY_REGISTERED_USER);
        }
    }

    // Username 으로 User 찾기
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
