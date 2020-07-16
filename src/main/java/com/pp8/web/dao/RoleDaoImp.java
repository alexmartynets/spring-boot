package com.pp8.web.dao;

import org.springframework.stereotype.Repository;
import com.pp8.web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void addRole(String role) {
      em.persist(role);
   }

   @Override
   public Role getRoleById(Long id) {
      return em.find(Role.class, id);
   }

   @Override
   public Role getRoleByName(String name) {
      return (Role) em.createQuery("from Role where name =:name")
              .setParameter("name", name)
              .getSingleResult();
   }

   @Override
   public List<Role> listRoles() {
      return em.createQuery("from Role").getResultList();
   };
}
