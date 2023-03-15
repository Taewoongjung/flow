package com.flow.adapter.inbound.api.room.webrequest;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CommandAddExtensionWebRequestV1 {

    @Size(min = 1, max = 20)
    private final String extension;
    private final String type;
}
