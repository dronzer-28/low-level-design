package StackOverflow.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Question extends Post{
    String title;
    List<Answer> answers = new ArrayList<>();
    Answer acceptedAnswer;
    Set<String> tags;

    public Question(String title, String body, Set<String> tags, User author){
        super(UUID.randomUUID().toString(), body, author);
        this.title = title;
        this.tags = tags;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }
    public synchronized void acceptAnswer(Answer answer){
        if(this.acceptedAnswer == null && this.answers.contains(answer)){
            acceptedAnswer = answer;
            answer.setAccepted(true);
        }
    }

    public String getTitle() {
        return title;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
    public Set<String> getTags() {
        return tags;
    }
}
