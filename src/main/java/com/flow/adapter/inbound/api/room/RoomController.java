package com.flow.adapter.inbound.api.room;

import com.flow.adapter.inbound.api.room.webrequest.CommandAddExtensionWebRequestV1;
import com.flow.application.room.RoomService;
import com.flow.application.room.request.AddExtensionRequest;
import com.flow.application.room.request.DeleteExtensionRequest;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.application.room.response.CommandDeleteExtensionResponse;
import com.flow.application.room.response.CommandGetAllExtensionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
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
            command.getExtensionName(),
            command.getType(),
            roomId
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(roomService.addExtension(commandService));
    }

    @DeleteMapping(value = "/room/{roomId}/extension/{extensionName}")
    public ResponseEntity<Object> deleteExtension(
        @PathVariable("roomId") final long roomId,
        @PathVariable("extensionName") final String extensionName
    ) {
        DeleteExtensionRequest commandService = new DeleteExtensionRequest(
            roomId,
            extensionName
        );

        roomService.deleteExtension(commandService);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/room/{roomId}")
    public ResponseEntity<CommandGetAllExtensionsResponse> getAllExtensions(
        @PathVariable final long roomId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(roomService.getAllExtensions(roomId));
    }
}
