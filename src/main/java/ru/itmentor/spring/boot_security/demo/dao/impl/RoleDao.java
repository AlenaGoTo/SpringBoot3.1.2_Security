package ru.itmentor.spring.boot_security.demo.dao.impl;

import ru.itmentor.spring.boot_security.demo.dao.EntityDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDao implements EntityDao<Role> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Role role) {
        em.merge(role);
        em.flush();
    }

    @Override
    public void deleteById(long id) {
        Role role = em.find(Role.class, id);
        em.remove(role);
        em.flush();
    }

    @Override
    public void update(long id, Role updatedRole) {
        Role role = em.find(Role.class, id);
        role.setRole(updatedRole.getRole());
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Optional<Role> getById(long id) {
        return Optional.ofNullable(em.find(Role.class, id));
    }

    @Override
    public Optional<Role> getByParam(String role) {
        return Optional.ofNullable(
                em.createQuery("from Role where role =: role",  Role.class)
                        .setParameter("role", role)
                        .getResultList()
                        .stream().findFirst().orElse(null));
    }
}
