package com.flow.domain.user.extension;

import static com.flow.extension.ExtensionFixture.EXTENSION;
import static com.flow.user.UserFixture.USER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.flow.adapter.common.exception.InvalidInputException;
import com.flow.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Extension 는 ")
class ExtensionTest {

    @DisplayName("생성된다")
    @Test
    void test1() {
        assertDoesNotThrow(() -> Extension.of (
            EXTENSION.getId(),
            EXTENSION.getName()
        ));
    }

    @DisplayName("필수값인 확장자 이름이 없을 때 생성 되지 않는다")
    @Test
    void test2() {
        assertThatThrownBy(() -> Extension.of (
            EXTENSION.getId(),
            null
        ))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("올바르지 않은 확장자 이름입니다.");
    }
}