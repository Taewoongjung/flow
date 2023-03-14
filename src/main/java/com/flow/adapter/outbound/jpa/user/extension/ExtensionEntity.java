package com.flow.adapter.outbound.jpa.user.extension;

import com.flow.adapter.outbound.jpa.user.UserEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "extension")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class ExtensionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public static ExtensionEntity of(final long id, final String name) {
        return new ExtensionEntity(id, name);
    }

    private ExtensionEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
