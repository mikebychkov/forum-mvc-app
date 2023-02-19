package org.mike.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String index(Model model) {

        model.addAttribute("users", userService.findAll());

        return "list";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") String id) {

        User user = userService.findByIdOrNew(id);
        model.addAttribute("user", user);

        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user) {

        userService.save(user);

        return "redirect:/users/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {

        userService.deleteById(id);

        return "redirect:/users/list";
    }
}
