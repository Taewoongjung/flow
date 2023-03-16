package com.flow.application.room;

import static com.flow.room.RoomFixture.ROOM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.domain.room.repository.RoomRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    private RoomRepository roomRepository = mock(RoomRepository.class);

    private RoomService roomService = new RoomService(roomRepository);

    @Test
    @DisplayName("확장자를 추가한다.")
    void addExtension() {
        // given
        AddExtensionRequest command = new AddExtensionRequest(
            "exe",
            "fixed",
            1L
        );

        // when
        when(roomRepository.findById(command.getRoomId()))
            .thenReturn(Optional.ofNullable(ROOM));

        // then
        CommandAddExtensionResponse actual = roomService.addExtension(command);
        assertThat(actual.getMessage()).isEqualTo("success");
    }
}