package com.fastcampus.fastcampusspringsecurity.exception;

import com.fastcampus.fastcampusspringsecurity.constant.ErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
