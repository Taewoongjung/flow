package com.flow.application.room;

import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_DUPLICATE;
import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_FULL;
import static com.flow.adapter.util.Util.check;
import static com.flow.domain.room.extension.ExtensionType.CUSTOM;

import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.request.DeleteExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.application.room.response.CommandDeleteExtensionResponse;
import com.flow.application.room.response.CommandGetAllExtensionsResponse;
import com.flow.application.room.response.ExtensionResponse;
import com.flow.domain.room.Room;
import com.flow.domain.room.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private String success = "success";

    @Transactional
    public CommandAddExtensionResponse addExtension(
        final AddExtensionRequest request
    ) {
        Room room = findRoomByRoomId(request.getRoomId());

        check(room.isDuplicateToAdd(request.getExtensionName()), INVALID_EXTENSION_DUPLICATE);
        check(room.isSizePossibleToAdd(), INVALID_EXTENSION_FULL);

        room.addExtension(request.getExtensionName(), request.getType());

        roomRepository.save(room);

        return new CommandAddExtensionResponse(
            success,
            request.getExtensionName(),
            request.getType()
        );
    }

    private Room findRoomByRoomId(final long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("룸을 찾지 못했습니다."));
    }

    @Transactional
    public CommandDeleteExtensionResponse deleteExtension(
        final DeleteExtensionRequest request
    ) {
        Room room = findRoomByRoomId(request.getRoomId());
        String extractedExtensionType =
            room.extractExtensionWith(request.getExtensionName());

        roomRepository.save(room);

        return new CommandDeleteExtensionResponse(
            success,
            request.getExtensionName(),
            extractedExtensionType
        );
    }

    @Transactional
    public CommandGetAllExtensionsResponse getAllExtensions(
        final long roomId
    ) {
        Room room = findRoomByRoomId(roomId);
        List<ExtensionResponse> responseList = getResponseList(room);

        return new CommandGetAllExtensionsResponse("success", responseList);
    }

    private List<ExtensionResponse> getResponseList(Room room) {
        return room.getExtensions().stream()
            .filter(a -> CUSTOM.equals(a.getExtensionType()))
            .map(a -> new ExtensionResponse(a.getId(), a.getName(), a.getExtensionType(), room.getId()))
            .collect(Collectors.toList());
    }
}
