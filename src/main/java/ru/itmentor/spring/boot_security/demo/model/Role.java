package ru.itmentor.spring.boot_security.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRoleName(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
