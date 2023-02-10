package org.mike.forum.dao.message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
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
