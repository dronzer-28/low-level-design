package StackOverflow.src;

import java.util.UUID;

public class Answer extends Post{
    String quesId;
    boolean isAccepted;

    public Answer(String quesId, String body, User author){
        super(UUID.randomUUID().toString(), body, author);
        this.quesId = quesId;
    }

    public void setAccepted(boolean status){
        this.isAccepted = status;
    }
    public boolean isAccepted(){
        return isAccepted;
    }
}
