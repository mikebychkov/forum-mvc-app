package org.mike.forum.dao.message;

import lombok.Data;
import org.mike.forum.dao.topic.Topic;
import org.mike.forum.dao.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("messages")
@Data
public class Message {

    @Id
    private String id;

    private Topic topic;

    private User author;

    private String text;
}
