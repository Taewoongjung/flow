package com.flow.domain.room.repository;

import com.flow.domain.room.Room;
import java.util.Optional;

public interface RoomRepository {

    public Room save(final Room room);

    public Optional<Room> findById(final long id);
}
