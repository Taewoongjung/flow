package com.flow.adapter.inbound.api.room.webrequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CommandAddExtensionWebRequestV1 {

    @Size(min = 1, max = 20)
    @NotBlank
    @NotEmpty
    private final String extensionName;
    private final String type;
}
