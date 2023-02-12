package org.mike.forum.dao.topic;

import lombok.RequiredArgsConstructor;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserService userService;

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic save(TopicDTO dto) {

        Topic topic = topicRepository.findById(dto.getId()).orElse(new Topic());

        topic.setName(dto.getName());
        topic.setArchive(dto.getArchive());
        if (topic.getId() == null || !dto.getAuthorId().equals(topic.getAuthor().getId())) {
            User newAuthor = userService.findById(dto.getAuthorId());
            topic.setAuthor(newAuthor);
        }

        return topicRepository.save(topic);
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic findById(String id) {
        return topicRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        topicRepository.deleteById(id);
    }
}
