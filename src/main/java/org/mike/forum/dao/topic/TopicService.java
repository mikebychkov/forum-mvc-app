package org.mike.forum.dao.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Topic save(Topic topic);
    Topic save(TopicDTO dto);
    Page<Topic> findAll(Pageable pageable);
    Topic findById(String id);
    void deleteById(String id);
}
