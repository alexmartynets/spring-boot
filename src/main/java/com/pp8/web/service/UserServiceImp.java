package com.pp8.web.service;

import com.pp8.web.dao.UserDao;
import com.pp8.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Transactional
   @Override
   public void add(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userDao.add(user);
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      userDao.updateUser(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByUsername(String username) {
      return userDao.getUserByUsername(username);
   }

   @Transactional
   @Override
   public void deleteUserById(Long id) {
      userDao.deleteUserById(id);
   }

   @Transactional
   @Override
   public UserDetails loadUserByUsername(String username) {
        Optional<User> userCandidate = Optional.ofNullable(userDao.getUserByUsername(username));
        return userCandidate.orElseThrow(IllegalArgumentException::new); // поменять ошибку
      // Аутентификация первичного пользователя dima / 123
//      User user = userDao.getUserByUsername(username);
//      user.setPassword(passwordEncoder.encode(user.getPassword()));
//      return user;
   }
}
