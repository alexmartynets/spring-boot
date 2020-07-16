package com.pp8.web.dao;

import org.springframework.stereotype.Repository;
import com.pp8.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void add(User user) {
      em.persist(user);
   }

   @Override
   public List<User> listUsers() {
      return em.createQuery("from User", User.class).getResultList();
   }

   @Override
   public User getUserById(Long id) {
      return em.find(User.class, id);
   }

   @Override
   public User getUserByUsername(String username) {

      return (User) em.createQuery("from User u where u.username =:username")
              .setParameter("username", username)
              .getSingleResult();   }

   @Override
   public void updateUser(User user) {
      em.merge(user); // для сохранения связных сущностей
   }

   @Override
   public void deleteUserById(Long id) {// сделать метод
      em.remove(getUserById(id));
   }
}
