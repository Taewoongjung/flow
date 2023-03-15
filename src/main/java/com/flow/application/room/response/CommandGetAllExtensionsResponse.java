package com.flow.application.room.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandGetAllExtensionsResponse {
    private final String message;
    private final List<ExtensionResponse> extensionList;

}
