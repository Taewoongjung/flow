package com.flow.domain.user.extension;

import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_NAME;
import static com.flow.adapter.util.Util.require;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Extension {
    private long id;
    private String name;

    public static Extension of(final long id, final String name) {
        require(o -> name == null, name, INVALID_EXTENSION_NAME);

        return new Extension(id, name);
    }

    private Extension(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
