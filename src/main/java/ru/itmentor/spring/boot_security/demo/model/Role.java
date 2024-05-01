package ru.itmentor.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// GrantedAuthority представляет полномочия, предоставленные объекту Authentication.
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    public Role() {}

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
