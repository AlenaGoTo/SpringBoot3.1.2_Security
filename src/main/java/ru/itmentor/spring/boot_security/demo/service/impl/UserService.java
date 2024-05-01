package ru.itmentor.spring.boot_security.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.dao.EntityDao;
import ru.itmentor.spring.boot_security.demo.service.EntityService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements EntityService<User> {

   @Autowired
   private EntityDao<User> userDao;

   @Autowired
   private EntityService<Role> roleService;

   @Transactional
   @Override
   public void save(User user) {
      userDao.save(user);
   }

   @Transactional
   @Override
   public void deleteById(long id) {
      userDao.deleteById(id);
   }

   @Transactional
   @Override
   public void update(long id, User user) {
      userDao.update(id, user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getAll() {
      return userDao.getAll();
   }

   @Transactional(readOnly = true)
   @Override
   public Optional<User> getById(long id) {
      return userDao.getById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public Optional<User> getByParam(String username) {
      return userDao.getByParam(username);
   }
}
