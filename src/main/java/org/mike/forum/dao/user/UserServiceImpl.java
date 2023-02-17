package org.mike.forum.dao.user;

import lombok.RequiredArgsConstructor;
import org.mike.forum.config.exceptionhandling.exceptions.UserNotFound;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }

    @Override
    public User findByIdForUpdate(String id) {
        return userRepository.findById(id)
                .map(User::new)
                .map(u -> {
                    u.setPassword(null);
                    return u;
                }).orElseThrow(UserNotFound::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {

        if (user.getId() != null && user.getPassword().isBlank()) {
            User dbuser = findById(user.getId());
            user.setPassword(dbuser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
