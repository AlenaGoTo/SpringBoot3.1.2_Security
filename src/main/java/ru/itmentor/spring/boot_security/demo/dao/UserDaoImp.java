package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void saveUser(User user) {
      em.merge(user);
      em.flush();

   }

   @Override
   public void removeUserById(long id) {
      User user = em.find(User.class, id);
      em.remove(user);
      em.flush();

   }

   @Override
   public List<User> getAllUsers() {
      return em.createQuery("from User", User.class).getResultList();
   }

   @Override
   public void updateUser(long id, String name, String lastname, byte age) {
      User user = em.find(User.class, id);
      user.setFirstName(name);
      user.setLastName(lastname);
      user.setAge(age);

   }

   @Override
   public User getUserById(long id) {
      return em.find(User.class,id);
   }

   @Override
   public Optional<User> getUserByParam(String username) {
      return Optional.ofNullable(
              em.createQuery("from User where username =: username",  User.class)
                      .setParameter("username", username)
                      .getResultList()
                      .stream().findFirst().orElse(null));
   }
}
