package StackOverflow.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Post extends Content{
    List<Comment> comments = new ArrayList<>();
    Map<String, VoteType> votes = new HashMap<>();
    int voteCount;
    
    public Post(String id, String body, User author) {
        super(id, body, author);  
        voteCount = 0; 
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
    public List<Comment> getComments(){
        return comments;
    }

    public synchronized void vote(User user, VoteType type){
        String userId = user.getUserId();
        if(votes.containsKey(userId)){
            if(type == votes.get(userId)){
                return;
            }
            else{
                voteCount += type == VoteType.UPVOTE? 1 : -1;
            }
        }
        else{
            voteCount += type == VoteType.UPVOTE? 1 : -1;
            votes.put(userId, type);
        }
    }

    public int getVoteCount(){
        return this.voteCount;
    }
}
