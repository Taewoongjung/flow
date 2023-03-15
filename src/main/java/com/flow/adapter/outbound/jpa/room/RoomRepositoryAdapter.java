package com.flow.adapter.outbound.jpa.room;

import static com.flow.adapter.outbound.jpa.room.RoomEntity.toRoomEntity;

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
        RoomEntity entity = toRoomEntity(room);
        jpaRepository.save(entity);
        return entity.toRoomReturn();
    }

    @Override
    public Optional<Room> findById(long id) {
        return jpaRepository.findById(id).map(RoomEntity::toRoomReturn);
    }
}
