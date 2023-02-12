package org.mike.forum.dao.topic;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mike.forum.dao.user.User;

import java.util.Optional;

@Data
@NoArgsConstructor
public class TopicDTO {

    private String id;
    private String authorId;
    private String authorName;
    private String name;
    private Boolean archive;

    public TopicDTO(Topic topic) {

        this.id = topic.getId();
        this.authorId = Optional.ofNullable(topic.getAuthor()).map(User::getId).orElse(null);
        this.authorName = Optional.ofNullable(topic.getAuthor()).map(User::getUsername).orElse(null);
        this.name = topic.getName();
        this.archive = topic.getArchive();
    }
}
