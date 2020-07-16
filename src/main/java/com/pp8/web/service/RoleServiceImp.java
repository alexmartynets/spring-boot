package com.pp8.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pp8.web.dao.RoleDao;
import com.pp8.web.model.Role;

import java.util.List;


@Service
public class RoleServiceImp implements RoleService {

   @Autowired
   private RoleDao roleDao;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Transactional
   @Override
   public void addRole(String role) {
      roleDao.addRole(role);
   }

   @Transactional(readOnly = true)
   @Override
   public List<Role> listRoles() {
      return roleDao.listRoles();
   }

   @Transactional(readOnly = true)
   @Override
   public Role getRoleById(Long id) {
      return roleDao.getRoleById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public Role getRoleByName(String name) {
      return roleDao.getRoleByName(name);
   }
}

