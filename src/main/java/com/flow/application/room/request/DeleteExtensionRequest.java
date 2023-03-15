package com.flow.application.room.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class DeleteExtensionRequest {
    private final long roomId;
    private final String extensionName;
}
