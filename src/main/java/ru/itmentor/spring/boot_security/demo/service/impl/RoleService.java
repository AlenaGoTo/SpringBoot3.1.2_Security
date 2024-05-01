package ru.itmentor.spring.boot_security.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.dao.EntityDao;
import ru.itmentor.spring.boot_security.demo.service.EntityService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements EntityService<Role> {

    @Autowired
    private EntityDao<Role> roleDao;

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        roleDao.deleteById(id);
    }

    @Transactional
    @Override
    public void update(long id, Role role) {
        roleDao.update(id, role);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> getById(long id) {
        return roleDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> getByParam(String role) {
        return roleDao.getByParam(role);
    }
}
