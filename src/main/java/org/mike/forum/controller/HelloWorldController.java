package org.mike.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("theDate", LocalDateTime.now());

        return "helloworld";
    }
}
