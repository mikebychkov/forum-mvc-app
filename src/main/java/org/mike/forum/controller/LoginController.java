package org.mike.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final UserService userService;

    @GetMapping("showLoginPage")
    public String login() {
        return "login";
    }

    @GetMapping("showAccessDenied")
    public String denied() {
        return "accessDenied";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/oops")
    public String error(Model model, HttpServletRequest req) {

        model.addAttribute("err", req.getParameter("err"));

        return "error";
    }
}
