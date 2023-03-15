package com.flow.adapter.inbound.api.room;

import com.flow.adapter.inbound.api.room.webrequest.CommandAddExtensionWebRequestV1;
import com.flow.application.room.RoomService;
import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/room/{roomId}")
    public ResponseEntity<CommandAddExtensionResponse> addExtension(
        @RequestBody final CommandAddExtensionWebRequestV1 command,
        @PathVariable final long roomId
    ) {
        AddExtensionRequest commandService = new AddExtensionRequest(
            command.getExtension(),
            command.getType()
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(roomService.addExtension(commandService, roomId));
    }

    @DeleteMapping(value = "/room/{roomId}/extension/{extensionId}")
    public ResponseEntity<Void> deleteExtension(
        @PathVariable final long roomId,
        @PathVariable final long extensionId
    ) {
        return null;
    }
}
