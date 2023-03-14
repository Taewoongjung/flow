package com.flow.domain.user;

import static com.flow.adapter.common.exception.ErrorType.INVALID_USER_EMAIL;
import static com.flow.adapter.common.exception.ErrorType.INVALID_USER_NAME;
import static com.flow.adapter.util.Util.require;

import com.flow.adapter.outbound.jpa.user.extension.ExtensionEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
    private List<ExtensionEntity> extensions = new ArrayList<>();

    public static User of(final long id, final String name, final String email) {
        require(o -> name == null, name, INVALID_USER_NAME);
        require(o -> email == null, email, INVALID_USER_EMAIL);

        return new User(id, name, email);
    }

    private User(final long id, final String name, final String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
