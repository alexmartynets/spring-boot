package com.pp8.web.controller;

import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.pp8.web.model.Role;
import com.pp8.web.model.User;
import com.pp8.web.service.RoleService;
import com.pp8.web.service.UserService;

//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("users")
public class UserController {

    private PasswordEncoder passwordEncoder;
    public UserService userService;
    public RoleService roleService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(path="list")
    public String listUserGetController(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "listUser";
    }

    @PostMapping(path="create")
    public String createUserPostController(ModelMap model, User user) {

        if ("".equals(user.getUsername()) | "".equals(user.getEmail()) | "".equals(user.getPassword()) | "".equals(user.getRoles())) {
            model.addAttribute("message", "Input correct data to register!");
            return "createUser";
        }
        userService.add(user);
        return "readUser";
    }

    @GetMapping(path="create")
    public String createUserGetController(ModelMap model) {
        model.addAttribute("roles", roleService.listRoles());
        return "createUser";
    }

    @GetMapping(path="update/{id}")
    public String updateUserGetController(ModelMap model, @PathVariable("id") Long id) {
//        model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping(path="update/{id}")
    public String updateUserPostController(ModelMap model, User user, @PathVariable("id") Long id) {
//        Set<Role> roles = new HashSet<>();
//        for (Role role: user.getRoles()) {
//            roles.add(roleService.getRoleByName(role.getName()));
//        }
//        user.setRoles(roles);

        User oldUser = userService.getUserById(id);
        user.setPassword(
                oldUser.getPassword().equals(user.getPassword()) ?
                oldUser.getPassword() :
                passwordEncoder.encode(user.getPassword()));

        userService.updateUser(user);
        model.addAttribute("message", "User successfully updated!");

        return "redirect:/users/list";
    }



    @PostMapping(path="delete")
    public String deleteUserGetController(ModelMap model,
                                          @RequestParam("deleteList") @Nullable String[] deleteList ) {

        if (deleteList == null) {
            model.addAttribute("message", "No Users to delete!");
        } else {
            Arrays.asList(deleteList).forEach(id -> {
                userService.deleteUserById(Long.parseLong(id));
                model.addAttribute("message", "Users successfully deleted!");
            });
        }
        model.addAttribute("users", userService.listUsers());
        return "listUser";
    }

    @GetMapping(path="read")
    public String readUserGetController(ModelMap model, HttpSession session) {
            model.addAttribute("user", session.getAttribute("user"));
        return "readUser";
    }
}
