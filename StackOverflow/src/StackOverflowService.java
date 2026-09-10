package StackOverflow.src;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowService {
    Map<String, User> users = new ConcurrentHashMap<>();
    Map<String, Question> questions = new ConcurrentHashMap<>();
    Map<String, Answer> answers = new ConcurrentHashMap<>();

    public User createUser(String name){
        User user = new User(name);
        users.put(user.getUserId(), user);
        return user;
    }

    public Question postQuestion(String title, String body, Set<String> tags, String userId){
        Question question = new Question(title, body, tags, users.get(userId));
        questions.put(question.getId(), question);
        return question;
    }

    public Answer postAnswer(String body, String userId, String quesId){
        Answer answer = new Answer(quesId, body, users.get(userId));
        Question question = questions.get(quesId);
        question.addAnswer(answer);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(String body, String userId, String postId){
        Comment comment = new Comment(body, users.get(userId), postId);
        Post post = getPostById(postId);
        post.addComment(comment);
        return comment;
    }

    public void vote(String userId, String postId, VoteType voteType){
        Post post = getPostById(postId);
        post.vote(users.get(userId), voteType);
    }

    public void acceptAnswer(String quesId, String ansId){
        Question question = questions.get(quesId);
        Answer answer = answers.get(ansId);
        question.acceptAnswer(answer);
    }

    private Post getPostById(String postId){
        if(questions.containsKey(postId)) return questions.get(postId);
        if(answers.containsKey(postId)) return answers.get(postId);

        throw new NoSuchElementException("Post not found");
    }
}
