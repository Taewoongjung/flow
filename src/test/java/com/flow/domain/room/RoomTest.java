package com.flow.domain.room;

import static com.flow.room.RoomFixture.ROOM;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.flow.adapter.common.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Room 은 ")
class RoomTest {

    @DisplayName("생성된다")
    @Test
    void test1() {
        assertDoesNotThrow(() -> Room.of (
            ROOM.getId(),
            ROOM.getName(),
            now(),
            now()
        ));
    }

    @DisplayName("필수값인 이름이 없을 때 생성 되지 않는다")
    @Test
    void test2() {
        assertThatThrownBy(() -> Room.of (
            ROOM.getId(),
            null,
            now(),
            now()
        ))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("올바르지 않은 이름입니다.");
    }
}