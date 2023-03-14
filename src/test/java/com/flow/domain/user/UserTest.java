package com.flow.domain.user;

import static com.flow.user.UserFixture.USER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.flow.adapter.common.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User 는 ")
class UserTest {

    @DisplayName("생성된다")
    @Test
    void test1() {
        assertDoesNotThrow(() -> User.of (
            USER.getId(),
            USER.getName(),
            USER.getEmail()
        ));
    }

    @DisplayName("필수값인 이름이 없을 때 생성 되지 않는다")
    @Test
    void test2() {
        assertThatThrownBy(() -> User.of (
            USER.getId(),
            null,
            USER.getEmail()
        ))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("올바르지 않은 이름입니다.");
    }

    @DisplayName("필수값인 이메일이 없을 때 생성 되지 않는다")
    @Test
    void test3() {
        assertThatThrownBy(() -> User.of (
            USER.getId(),
            USER.getName(),
            null
        ))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("올바르지 않은 이메일입니다.");
    }
}