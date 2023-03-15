package com.flow.application.room;

import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_DUPLICATE;
import static com.flow.adapter.common.exception.ErrorType.INVALID_EXTENSION_FULL;
import static com.flow.adapter.util.Util.check;

import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.domain.room.Room;
import com.flow.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public CommandAddExtensionResponse addExtension(
        final AddExtensionRequest request,
        final long roomId
    ) {
        Room room = findRoomByRoomId(roomId);

        check(room.isDuplicateToAdd(request.getExtensionName()), INVALID_EXTENSION_DUPLICATE);
        check(room.isSizePossibleToAdd(), INVALID_EXTENSION_FULL);

        room.addExtension(request.getExtensionName(), request.getType());

        roomRepository.save(room);

        return new CommandAddExtensionResponse(
            "success"
        );
    }

    private Room findRoomByRoomId(final long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("룸을 찾지 못했습니다."));
    }
}
