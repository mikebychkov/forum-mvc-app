package org.mike.forum.config.exceptionhandling.exceptions;

public class UserNotFound extends RuntimeException {

    public UserNotFound() {
        super("User not found");
    }
}
