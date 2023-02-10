package org.mike.forum.dao.topic;

import lombok.Data;
import org.mike.forum.dao.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topics")
@Data
public class Topic {

    @Id
    private String id;

    @DBRef
    private User author;

    private String name;

    private Boolean archive;

    public TopicDTO toDTO() {
        return new TopicDTO(this);
    }
}
