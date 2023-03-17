package com.flow.application.room;

import static com.flow.extension.ExtensionFixture.EXTENSION;
import static com.flow.room.RoomFixture.ROOM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.request.DeleteExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.application.room.response.CommandDeleteExtensionResponse;
import com.flow.application.room.response.CommandGetAllExtensionsResponse;
import com.flow.application.room.response.ExtensionResponse;
import com.flow.domain.room.extension.Extension;
import com.flow.domain.room.repository.RoomRepository;
import java.util.ArrayList;
import java.util.List;
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
    void addExtension_test1() {
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
        assertThat(actual.getAddedExtensionName()).isEqualTo("exe");
        assertThat(actual.getAddedExtensionType()).isEqualTo("fixed");
    }

    @Test
    @DisplayName("룸 정보를 잘못 입력했을 때 룸을 찾지 못한다.")
    void addExtension_test2() {
        // given
        AddExtensionRequest command = new AddExtensionRequest(
            "exe",
            "fixed",
            10L
        );

        // when
        when(roomRepository.findById(command.getRoomId()))
            .thenReturn(Optional.ofNullable(null));

        // then
        assertThrows(IllegalArgumentException.class,
            () -> roomService.addExtension(command),
            "룸을 찾지 못했습니다.");
    }

    @Test
    @DisplayName("확장자를 제거한다.")
    void deleteExtension_test1() {
        // given
        ROOM.getExtensions().add(EXTENSION);
        DeleteExtensionRequest command = new DeleteExtensionRequest(
            1L,
            "exe"
        );

        // when
        when(roomRepository.findById(command.getRoomId()))
            .thenReturn(Optional.ofNullable(ROOM));

        CommandDeleteExtensionResponse actual = roomService.deleteExtension(command);

        assertThat(actual.getMessage()).isEqualTo("success");
        assertThat(actual.getDeletedExtensionName()).isEqualTo("exe");
        assertThat(actual.getDeletedExtensionType()).isEqualTo("FIXED");
    }

    @Test
    @DisplayName("확장자를 모두 불러온다.")
    void getAllExtensions_test1() {
        // given
        long roomId = 1L;

        ROOM.getExtensions().add(EXTENSION);
        Extension extension = getElement(ROOM.getExtensions());

        ExtensionResponse response = new ExtensionResponse(
            extension.getId(),
            extension.getName(),
            extension.getExtensionType(),
            1L
        );

        List<ExtensionResponse> responseList = new ArrayList<>();
        responseList.add(response);

        // when
        when(roomRepository.findById(roomId))
            .thenReturn(Optional.ofNullable(ROOM));

        // then
        CommandGetAllExtensionsResponse actual = roomService.getAllExtensions(roomId);
        actual.getExtensionList().add(response);

        assertThat(actual.getMessage()).isEqualTo("success");
        assertThat(actual.getExtensionList()).isEqualTo(responseList);
    }

    private Extension getElement(List<Extension> extension) {
        return extension.get(0);
    }
}