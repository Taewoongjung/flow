package com.flow.application.room.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandDeleteExtensionResponse {
    private final String message;
    private final String deletedExtensionName;
    private final String deletedExtensionType;
}
