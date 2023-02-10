package org.mike.forum.dao.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    Message save(Message message);
    Page<Message> findAllByTopicId(String topicId, Pageable pageable);
    Message findById(String id);
    void deleteById(String id);
}
