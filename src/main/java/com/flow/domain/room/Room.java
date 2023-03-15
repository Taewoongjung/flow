package com.flow.domain.room;

import static com.flow.adapter.common.exception.ErrorType.INVALID_ROOM_NAME;
import static com.flow.adapter.util.Util.require;

import com.flow.domain.room.extension.Extension;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Room {
    private long id;
    private String name;

    private List<Extension> extensions = new ArrayList<>();

    public static Room of(final long id, final String name) {
        require(o -> name == null, name, INVALID_ROOM_NAME);

        return new Room(id, name);
    }

    private Room(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public void addExtension(
        final String name,
        final String type
    ) {
        Extension extension = Extension.of(name, type);
        extensions.add(extension);
    }
}
