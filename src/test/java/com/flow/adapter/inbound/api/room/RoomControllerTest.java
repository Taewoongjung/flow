package com.flow.adapter.inbound.api.room;

import static com.flow.domain.room.extension.ExtensionType.FIXED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.adapter.inbound.api.room.webrequest.CommandAddExtensionWebRequestV1;
import com.flow.application.room.RoomService;
import com.flow.application.room.response.CommandAddExtensionResponse;
import com.flow.application.room.response.CommandDeleteExtensionResponse;
import com.flow.application.room.response.CommandGetAllExtensionsResponse;
import com.flow.application.room.response.ExtensionResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RoomController.class)
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomService roomService;

    @Test
    @DisplayName("확장자를 추가한다.")
    void addExtension() throws Exception {
        // given
        CommandAddExtensionWebRequestV1 command = new CommandAddExtensionWebRequestV1(
            "exe",
            "fixed"
        );
        long roomId = 1L;

        CommandAddExtensionResponse response = new CommandAddExtensionResponse(
            "success", command.getExtensionName(), command.getType()
        );

        given(roomService.addExtension(any())).willReturn(response);

        mockMvc.perform(
        // when
            post("/room/{roomId}", roomId)
                .content(objectMapper.writeValueAsString(command))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
        // then
            .andExpectAll(
                status().isOk(),
                content().string(objectMapper.writeValueAsString(response))
            )

        // docs
            .andDo(document("extensions/extension-add (확장자 등록)",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("roomId").description("룸 ID")
                ),
                requestFields(
                    fieldWithPath("extensionName").type(JsonFieldType.STRING).description("확장자 이름"),
                    fieldWithPath("type").type(JsonFieldType.STRING).description("확장자 타입")
                ),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                    fieldWithPath("addedExtensionName").type(JsonFieldType.STRING).description("확장자 이름"),
                    fieldWithPath("addedExtensionType").type(JsonFieldType.STRING).description("확장자 타입")
                )
        ));
    }

    @Test
    @DisplayName("확장자를 삭제한다.")
    void deleteExtension() throws Exception {
        // given
        long roomId = 1L;
        String extensionName = "exe";

        CommandDeleteExtensionResponse response = new CommandDeleteExtensionResponse(
            "success",
            "exe",
            "FIXED"
        );

        given(roomService.deleteExtension(any())).willReturn(response);

        mockMvc.perform(
        // when
                delete("/room/{roomId}/extension/{extensionName}", roomId, extensionName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
        // then
            .andExpect(status().isNoContent())

        // docs
            .andDo(document("extensions/extension-delete (확장자 삭제)",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("roomId").description("룸 ID"),
                    parameterWithName("extensionName").description("확장자 이름")
                )
            ));
    }

    @Test
    @DisplayName("모든 확장자를 조회한다.")
    void getAllExtensions() throws Exception {
        // given
        long roomId = 1L;

        ExtensionResponse extensionResponse1 = new ExtensionResponse(
            1L,
            "exe",
            FIXED,
            1L
        );
        ExtensionResponse extensionResponse2 = new ExtensionResponse(
            2L,
            "js",
            FIXED,
            1L
        );
        List<ExtensionResponse> extensionResponseList = new ArrayList<>();
        extensionResponseList.add(extensionResponse1);
        extensionResponseList.add(extensionResponse2);

        CommandGetAllExtensionsResponse response = new CommandGetAllExtensionsResponse(
            "success",
            extensionResponseList
        );

        given(roomService.getAllExtensions(anyLong())).willReturn(response);

        mockMvc.perform(
        // when
                get("/room/{roomId}", roomId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
        // then
            .andExpectAll(
                status().isOk(),
                content().string(objectMapper.writeValueAsString(response))
            )

        // docs
            .andDo(document("extensions/extension-getAll (확장자 모두 조회)",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("roomId").description("룸 ID")
                ),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                    fieldWithPath("extensionList[].id").type(JsonFieldType.NUMBER).description("확장자 ID"),
                    fieldWithPath("extensionList[].name").type(JsonFieldType.STRING).description("확장자 이름"),
                    fieldWithPath("extensionList[].type").type(JsonFieldType.STRING).description("확장자 타입"),
                    fieldWithPath("extensionList[].roomId").type(JsonFieldType.NUMBER).description("룸 ID")
                )
            ));
    }
}
