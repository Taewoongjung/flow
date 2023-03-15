package com.flow.adapter.outbound.jpa.room;

import com.flow.adapter.outbound.jpa.BaseEntity;
import com.flow.adapter.outbound.jpa.room.extension.ExtensionEntity;
import com.flow.domain.room.Room;
import com.flow.domain.room.extension.Extension;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RoomEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtensionEntity> extensions = new ArrayList<>();

    public static RoomEntity of(final long id, final String name) {
        return new RoomEntity(id, name);
    }

    private RoomEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static RoomEntity toRoomEntity(Room room) {
        return RoomEntity.of(room.getId(), room.getName());
    }

    public Room toRoomReturn() {
        Room room = Room.of(id, name);
        extensions.forEach(extensionEntity -> room.getExtensions().add(
                Extension.of(
                    extensionEntity.getId(),
                    extensionEntity.getName(),
                    String.valueOf(extensionEntity.getExtensionType())
                )
            ));

        return room;
    }
}
