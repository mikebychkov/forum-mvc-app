package org.mike.forum.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloRestController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello() {

        return helloService.sayHello();
    }
}
