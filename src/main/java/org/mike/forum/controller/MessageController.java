package org.mike.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mike.forum.config.CurrentUser;
import org.mike.forum.dao.message.MessageDTO;
import org.mike.forum.dao.message.MessageService;
import org.mike.forum.dao.topic.Topic;
import org.mike.forum.dao.topic.TopicService;
import org.mike.forum.dao.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/messages")
@Log4j2
public class MessageController {

    private final MessageService messageService;
    private final TopicService topicService;

    @GetMapping("/list")
    public String index(Model model, @RequestParam("topicId") String topicId) {

        // TODO: implement pagination
        Pageable pageable = Pageable.ofSize(10);

        Topic topic = topicService.findById(topicId);

        model.addAttribute("messages", messageService.findAllByTopicId(topicId, pageable));
        model.addAttribute("topic", topic);

        return "messages";
    }

    @GetMapping("/new")
    public String newTopic(Model model, @CurrentUser User user, @RequestParam("topicId") String topicId) {

        Topic topic = topicService.findById(topicId);

        MessageDTO msg = new MessageDTO();
        msg.setTopicId(topic.getId());
        msg.setTopicName(topic.getName());
        msg.setAuthorId(user.getId());
        msg.setAuthorName(user.getUsername());

        model.addAttribute("msg", msg);

        return "message-edit";
    }

    @GetMapping("/edit")
    public String editTopic(Model model, @CurrentUser User user, @RequestParam("id") String id) {

        MessageDTO msg = messageService.findById(id).toDTO();

        model.addAttribute("msg", msg);

        return "message-edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("topic") MessageDTO msg) {

        messageService.save(msg);

        return "redirect:/messages/list?topicId=" + msg.getTopicId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, @RequestParam("topicId") String topicId) {

        messageService.deleteById(id);

        return "redirect:/messages/list?topicId=" + topicId;
    }
}
