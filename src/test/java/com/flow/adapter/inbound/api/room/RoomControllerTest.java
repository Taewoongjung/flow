package com.flow.adapter.inbound.api.room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
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
            post("/room/{roomId}", roomId)
                .content(objectMapper.writeValueAsString(command))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().string(objectMapper.writeValueAsString(response))
            )

            .andDo(document("extensions/extension-add",
                Preprocessors.preprocessRequest(prettyPrint()),
                Preprocessors.preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("roomId").description("roomId")
                ),
                requestFields(
                    fieldWithPath("extensionName").type(JsonFieldType.STRING).description("extensionName"),
                    fieldWithPath("type").type(JsonFieldType.STRING).description("extensionType")
                ),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("message"),
                    fieldWithPath("addedExtensionName").type(JsonFieldType.STRING).description("extensionName"),
                    fieldWithPath("addedExtensionType").type(JsonFieldType.STRING).description("extensionType")
                )
        ));
    }
}