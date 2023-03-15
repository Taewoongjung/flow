package com.flow.domain.room.extension;

import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_TYPE;

import com.flow.adapter.common.exception.InvalidInputException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtensionType {
    FIXED("fixed"),
    CUSTOM("custom")
    ;

    private final String extensionType;

    public String getExtensionType() {
        return extensionType;
    }

    public static ExtensionType from(final String extensionType) {
        return Arrays.stream(values())
            .filter(value -> value.getExtensionType().equalsIgnoreCase(extensionType))
            .findFirst()
            .orElseThrow(() -> new InvalidInputException(INVALID_EXTENSION_TYPE));
    }

    public static boolean checkIfCustom(ExtensionType type) {
        return type == CUSTOM;
    }
}
