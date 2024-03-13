package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;


@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    @Column(unique = true)
    private String name;

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
