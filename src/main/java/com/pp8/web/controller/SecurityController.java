package com.pp8.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pp8.web.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("security")
public class SecurityController {

    @GetMapping(path="login")
    public String loginUserGetController(ModelMap model, HttpServletRequest request, User user) {

        if (request.getParameterMap().containsKey("error")) { // @RequestParam забирает только те параметры, у которых есть значения
            model.addAttribute("message", "Enter correct login data!");
        }
        return "loginUser";
    }


    @PostMapping(path="login")
    public String loginUserPostController(ModelMap model, HttpServletRequest request, User user) {

        if (request.getParameterMap().containsKey("error") || "".equals(user.getUsername()) || "".equals(user.getPassword()) || (user.getId() == null)) { // @RequestParam забирает только те параметры, у которых есть значения
            model.addAttribute("message", "Enter correct login data!");
            return "loginUser";
        }
        model.addAttribute("user", user);
        return "redirect:/users/read";
    }
}
