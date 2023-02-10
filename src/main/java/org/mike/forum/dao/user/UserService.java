package org.mike.forum.dao.user;

import java.util.List;

public interface UserService {

    User findById(String id);
    User findByIdForUpdate(String id);
    User findByUsername(String username);
    List<User> findAll();
    User save(User user);
    void deleteById(String id);
}
