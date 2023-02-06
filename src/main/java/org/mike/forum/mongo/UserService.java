package org.mike.forum.mongo;

import java.util.List;

public interface UserService {

    User findById(String id);
    User findByIdForUpdate(String id);
    User findByUsername(String username);
    List<User> findAll();
    User save(User user);
    void deleteById(String id);
}
