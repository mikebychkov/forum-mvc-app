package org.mike.forum.dao.message;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.topic.Topic;
import org.mike.forum.dao.topic.TopicService;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final TopicService topicService;
    private final UserService userService;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message save(MessageDTO messageDTO) {

        Message msg = messageRepository.findById(messageDTO.getId()).orElse(new Message());

        msg.setText(messageDTO.getText());

        if (msg.getId() == null || messageDTO.getTopicId().equals(msg.getTopic().getId())) {
            Topic newTopic = topicService.findById(messageDTO.getTopicId());
            msg.setTopic(newTopic);
        }

        if (msg.getId() == null || messageDTO.getAuthorId().equals(msg.getAuthor().getId())) {
            User newAuthor = userService.findById(messageDTO.getAuthorId());
            msg.setAuthor(newAuthor);
        }

        msg.setEdited(LocalDateTime.now());

        return messageRepository.save(msg);
    }

    @Override
    public Page<Message> findAllByTopicId(String topicId, Pageable pageable) {
        return messageRepository.findAllByTopicId(topicId, pageable);
    }

    @Override
    public Message findById(String id) {
        return messageRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        messageRepository.deleteById(id);
    }
}
