package com.cloudstorage.user.model;

import com.cloudstorage.common.domain.user.User;
import com.cloudstorage.common.model.BaseEntityModel;
import com.cloudstorage.common.repository.converter.StringUuidConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Entity(name = "user_info")
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntityModel<Long> {
    @JdbcTypeCode(SqlTypes.JSON)
    private User data;
    @Convert(converter = StringUuidConverter.class)
    private String keycloakUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
