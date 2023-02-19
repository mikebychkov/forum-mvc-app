package org.mike.forum.controller.test;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserDTO;
import org.mike.forum.dao.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {

        return userService.findById(id).toDTO();
    }

    @GetMapping
    public List<UserDTO> getUserList() {

        return userService.findAll().stream().map(User::toDTO).toList();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userService.save(userDTO).toDTO();
    }
}
