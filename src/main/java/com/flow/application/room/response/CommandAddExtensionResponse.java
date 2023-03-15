package com.flow.application.room.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandAddExtensionResponse {
    private final String message;
    private final long addedExtensionId;
}
