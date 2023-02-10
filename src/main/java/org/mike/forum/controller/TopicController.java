package org.mike.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mike.forum.config.CurrentUser;
import org.mike.forum.dao.topic.TopicDTO;
import org.mike.forum.dao.topic.TopicService;
import org.mike.forum.dao.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topics")
@Log4j2
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/list")
    public String index(Model model) {

        Pageable pageable = Pageable.ofSize(10);

        model.addAttribute("topics", topicService.findAll(pageable));

        return "topics";
    }

    @GetMapping("/new")
    public String newTopic(Model model, @CurrentUser User user) {

        TopicDTO topic = new TopicDTO();
        topic.setAuthorId(user.getId());
        topic.setAuthorName(user.getUsername());

        model.addAttribute("topic", topic);

        return "topic-edit";
    }

    @GetMapping("/edit")
    public String editTopic(Model model, @CurrentUser User user, @RequestParam("id") String id) {

        TopicDTO topic = topicService.findById(id).toDTO();

        model.addAttribute("topic", topic);

        return "topic-edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("topic") TopicDTO topic) {

        topicService.save(topic);

        return "redirect:/topics/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {

        topicService.deleteById(id);

        return "redirect:/topics/list";
    }
}
