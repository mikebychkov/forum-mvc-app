package org.mike.forum.dao.user;

import java.util.List;

public interface UserService {

    User findById(String id);
    User findByIdOrNew(String id);
    User findByUsername(String username);
    List<User> findAll();
    User save(User user);
    User save(UserDTO dto);
    void deleteById(String id);
}
