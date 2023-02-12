package org.mike.forum.dao.message;

import lombok.Data;
import org.mike.forum.dao.topic.Topic;
import org.mike.forum.dao.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("messages")
@Data
public class Message {

    @Id
    private String id;
    @DBRef
    private Topic topic;
    @DBRef
    private User author;
    private String text;
    private LocalDateTime edited;

    public MessageDTO toDTO() {
        return new MessageDTO(this);
    }
}
