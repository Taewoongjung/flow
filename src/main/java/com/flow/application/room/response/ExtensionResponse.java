package com.flow.application.room.response;

import com.flow.domain.room.extension.ExtensionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExtensionResponse {

    private final long id;
    private final String name;
    private final ExtensionType type;
    private final long roomId;
}
