package com.pp8.web.service;

import com.pp8.web.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(String role);
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> listRoles();

}
