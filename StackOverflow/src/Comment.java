package StackOverflow.src;

import java.util.UUID;

public class Comment extends Content{
    String postId;

    public Comment(String body, User author, String postId){
        super(UUID.randomUUID().toString(), body, author);
        this.postId = postId;
    }
}
