package org.mike.forum.controller.test;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "HELLO TINY FORUM!";
    }
}
