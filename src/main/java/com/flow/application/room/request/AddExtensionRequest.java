package com.flow.application.room.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class AddExtensionRequest {
    private final String extensionName;
    private final String type;
}
