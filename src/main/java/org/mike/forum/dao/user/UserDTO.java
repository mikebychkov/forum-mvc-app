package org.mike.forum.dao.user;

import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String username;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
