package com.flow.adapter.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    INVALID_USER_NAME(1001, "올바르지 않은 이름입니다."),
    INVALID_USER_EMAIL(1002, "올바르지 않은 이메일입니다."),

    INVALID_EXTENSION_NAME(2001, "올바르지 않은 확장자 이름입니다."),
    ;

    private final int code;
    private final String message;
}
