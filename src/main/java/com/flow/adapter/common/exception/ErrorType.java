package com.flow.adapter.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    INVALID_ROOM_NAME(1001, "올바르지 않은 이름입니다."),

    INVALID_EXTENSION_NAME(2001, "올바르지 않은 확장자 이름입니다."),
    INVALID_EXTENSION_TYPE(2002, "올바르지 않은 확장자 타입입니다."),
    INVALID_EXTENSION_DUPLICATE(2003, "반복되는 커스텀 확장자 입니다."),
    INVALID_EXTENSION_FULL(2004, "추가할 수 있는 커스텀 확장자가 한계에 도달했습니다.")
    ;

    private final int code;
    private final String message;
}
