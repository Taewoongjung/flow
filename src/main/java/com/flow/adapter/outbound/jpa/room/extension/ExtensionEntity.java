package com.flow.adapter.outbound.jpa.room.extension;

import com.flow.adapter.outbound.jpa.room.RoomEntity;
import com.flow.domain.room.extension.ExtensionType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "extension")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class ExtensionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private ExtensionType extensionType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomEntity room;

    public static ExtensionEntity of(final long id, final String name, final String type) {
        return new ExtensionEntity(id, name, type);
    }

    private ExtensionEntity(final long id, final String name, final String type) {
        this.id = id;
        this.name = name;
        this.extensionType = ExtensionType.from(type);
    }
}
