package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void saveUser(User user) {
      em.persist(user);
      //em.flush();

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
   public void dropUsersTable() {
      em.createNativeQuery("DROP TABLE IF EXISTS USERS_WEB ").executeUpdate();
   }

   @Override
   public void createUsersTable() {
      em.createNativeQuery("CREATE TABLE IF NOT EXISTS USERS_WEB " +
              "(ID            SERIAL PRIMARY KEY     NOT NULL," +
              " NAME          VARCHAR(255)    NOT NULL, " +
              " LASTNAME      VARCHAR(255)    NOT NULL, " +
              " AGE           INT     NOT NULL)").executeUpdate();
   }

   @Override
   public void cleanUsersTable() {
      em.createNativeQuery("DELETE from USERS_WEB").executeUpdate();
   }
}
