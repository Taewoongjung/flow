package com.flow.domain.room.extension;

import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_NAME;
import static com.flow.adapter.util.Util.require;

import com.flow.domain.room.Room;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Extension {
    private long id;
    private String name;
    private ExtensionType extensionType;

    private Room room;

    public static Extension of(final long id, final String name, final String type) {
        require(o -> name == null, name, INVALID_EXTENSION_NAME);

        return new Extension(id, name, type);
    }

    public static Extension of(final String name, final String type) {
        require(o -> name == null, name, INVALID_EXTENSION_NAME);

        return new Extension(name, type);
    }

    private Extension(final String name, final String type) {
        this.name = name;
        this.extensionType = ExtensionType.from(type);
    }

    private Extension(final long id, final String name, final String type) {
        this.id = id;
        this.name = name;
        this.extensionType = ExtensionType.from(type);
    }

}
