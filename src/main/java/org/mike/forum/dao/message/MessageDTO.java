package org.mike.forum.dao.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {

    private String id;
    private String text;
    private String topicId;
    private String topicName;
    private String authorId;
    private String authorName;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.text = message.getText();
        this.topicId = message.getTopic().getId();
        this.topicName = message.getTopic().getName();
        this.authorId = message.getAuthor().getId();
        this.authorName = message.getAuthor().getUsername();
    }
}
