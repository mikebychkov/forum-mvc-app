package org.mike.forum.controller.test;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.user.UserDTO;
import org.mike.forum.dao.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {

        return userService.findById(id).toDTO();
    }
}
