package com.flow.room;

import static java.time.LocalDateTime.now;

import com.flow.domain.room.Room;

public class RoomFixture {

    public static Room ROOM = Room.of(1L, "길동홍", now(), now());
}
