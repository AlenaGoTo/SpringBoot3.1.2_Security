package ru.itmentor.spring.boot_security.demo.dao.impl;

import ru.itmentor.spring.boot_security.demo.dao.EntityDao;
import ru.itmentor.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements EntityDao<User> {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void save(User user) {
      em.merge(user);
      em.flush();
   }

   @Override
   public void deleteById(long id) {
      User user = em.find(User.class, id);
      em.remove(user);
      em.flush();
   }

   @Override
   public void update(long id, User updatedUser) {
      User user = em.find(User.class, id);
      user.setFirstName(updatedUser.getFirstName());
      user.setLastName(updatedUser.getLastName());
      user.setAge(updatedUser.getAge());
   }

   @Override
   public List<User> getAll() {
      return em.createQuery("from User", User.class).getResultList();
   }

   @Override
   public Optional<User> getById(long id) {
      return Optional.ofNullable(em.find(User.class, id));
   }

   @Override
   public Optional<User> getByParam(String username) {
      return Optional.ofNullable(
              em.createQuery("from User where username =: username",  User.class)
                      .setParameter("username", username)
                      .getResultList()
                      .stream().findFirst().orElse(null));
   }
}
