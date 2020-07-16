package com.pp8.web.dao;

import com.pp8.web.model.User;

import java.util.List;

public interface UserDao {

   void add(User user);
   User getUserById(Long id);
   User getUserByUsername(String username);
   void updateUser(User user);
   void deleteUserById(Long id);
   List<User> listUsers();
}
