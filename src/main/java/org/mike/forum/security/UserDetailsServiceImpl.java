package org.mike.forum.security;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.user.UserService;
import org.mike.forum.dao.user.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService  {

    private final UserService userService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

//        var currentUser = userService.findByUsername(username);
//
//        UserBuilder builder = User.withUsername(username);
//        builder.password(currentUser.getPassword());
//        builder.authorities(currentUser.getRoles().split(","));
//
//        return builder.build();

        return userService.findByUsername(username);
    }
}