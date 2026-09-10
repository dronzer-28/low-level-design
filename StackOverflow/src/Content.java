package StackOverflow.src;

import java.time.LocalDateTime;

abstract class Content {
    String id;
    String body;
    User author;
    LocalDateTime createdAt;

    public Content(String id, String body, User author){
        this.id = id;
        this.body = body;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    } 
}
