package com.flow.adapter.outbound.jpa.user;

import com.flow.adapter.outbound.jpa.user.extension.ExtensionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtensionEntity> extensions = new ArrayList<>();

    public static UserEntity of(final long id, final String name, final String email) {
        return new UserEntity(id, name, email);
    }

    private UserEntity(final long id, final String name, final String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
