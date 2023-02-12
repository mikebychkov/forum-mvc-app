package org.mike.forum.security;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService  {

    private final UserService userService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findByUsername(username);
    }
}