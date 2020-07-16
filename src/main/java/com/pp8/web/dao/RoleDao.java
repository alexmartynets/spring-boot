package com.pp8.web.dao;

import com.pp8.web.model.Role;

import java.util.List;

public interface RoleDao {
   void addRole(String role);
   Role getRoleById(Long id);
   Role getRoleByName(String rname);
   List<Role> listRoles();
}
