package com.flow.domain.room;

import static com.flow.adapter.common.exception.ErrorType.INVALID_ROOM_NAME;
import static com.flow.adapter.util.Util.require;
import static com.flow.domain.room.extension.ExtensionType.checkIfCustom;

import com.flow.domain.room.extension.Extension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString(exclude = {"extensions"})
@RequiredArgsConstructor
public class Room {
    private long id;
    private String name;
    private LocalDateTime lastModified;
    private LocalDateTime createdAt;

    private List<Extension> extensions = new ArrayList<>();

    public static Room of(
        final long id,
        final String name,
        final LocalDateTime lastModified,
        final LocalDateTime createdAt
    ) {
        require(o -> name == null, name, INVALID_ROOM_NAME);

        return new Room(id, name, lastModified, createdAt);
    }

    private Room(
        final long id,
        final String name,
        final LocalDateTime lastModified,
        final LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.lastModified = lastModified;
        this.createdAt = createdAt;
    }

    public void addExtension(
        final String name,
        final String type
    ) {
        Extension extension = Extension.of(name, type);
        extensions.add(extension);
    }

    public boolean isSizePossibleToAdd() {
        long countSizeOfCustomExtensions = this.extensions.stream()
            .filter(e -> checkIfCustom(e.getExtensionType()))
            .count();

        return countSizeOfCustomExtensions >= 200;
    }

    public boolean isDuplicateToAdd(final String requestExtensionName) {
        Optional<Extension> duplicateExtension = this.extensions.stream()
            .filter(e -> checkSameExtension(requestExtensionName, e.getName()))
            .findFirst();
        return duplicateExtension.isPresent();
    }

    private boolean checkSameExtension(
        final String requestExtensionName,
        final String dbExtensionName
    ) {
        return dbExtensionName.equals(requestExtensionName);
    }

    public String extractExtensionWith(final String extensionName) {
        Optional<Extension> extension = this.extensions.stream()
            .filter(e -> checkSameExtension(extensionName, e.getName()))
            .findFirst();

        extensions.remove(extension.get());
        return extractExtensionType(extension.get());
    }

    private String extractExtensionType(Extension extension) {
        return String.valueOf(extension.getExtensionType());
    }
}
