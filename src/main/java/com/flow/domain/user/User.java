package com.flow.domain.user;

import com.flow.adapter.outbound.jpa.user.extension.ExtensionEntity;
import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String email;
    private List<ExtensionEntity> extensions = new ArrayList<>();
}
