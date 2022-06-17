package com.fastcampus.fastcampusspringsecurity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("유저를 찾을 수 없습니다"),
    ALREADY_REGISTERED_USER("이미 등록된 유저입니다");

    private final String message;
}
