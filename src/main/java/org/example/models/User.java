package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "my_user", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER
//            ,
//            cascade = CascadeType.ALL
            )
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "role_id",
                            referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {

    }

    public User(
            String email, String password,
            Collection<Role> roles) {

        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
