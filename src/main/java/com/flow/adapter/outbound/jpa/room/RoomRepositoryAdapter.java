package com.flow.adapter.outbound.jpa.room;

import static com.flow.adapter.outbound.jpa.room.RoomEntity.toRoomEntityWithExtensions;

import com.flow.domain.room.Room;
import com.flow.domain.room.repository.RoomRepository;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class RoomRepositoryAdapter implements RoomRepository {

    private final RoomJpaRepository jpaRepository;

    @Override
    public Room save(Room room) {
        RoomEntity entity = toRoomEntityWithExtensions(room);
        jpaRepository.save(entity);
        return entity.toRoomReturn();
    }

    @Override
    public Optional<Room> findById(long id) {
        Optional<RoomEntity> foundRoomEntity = jpaRepository.findById(id);
        return Optional.ofNullable(foundRoomEntity.get().toRoomReturn());
    }
}
