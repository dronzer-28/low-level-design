package StackOverflow.src;
import java.util.HashSet;
import java.util.Set;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflowService service = new StackOverflowService();

        // create users
        User bob = service.createUser("Bob");
        User alice = service.createUser("Alice");
        User charlie = service.createUser("Charlie");

        // post question
        System.out.println("Alice asking a question!");
        Set<String> tags = new HashSet<>(Set.of("lld", "system-design", "interview"));
        Question alice_question = service.postQuestion("How to do LLD problem?", "Details about LLD delivery framework", tags, alice.getUserId());

        // post answer
        Answer answer1 = service.postAnswer("use the following method ...", bob.getUserId(), alice_question.getId());
        Answer answer2 = service.postAnswer("use the following method ...", charlie.getUserId(), alice_question.getId());

        // voting
        System.out.println("\n--- Voting Occurs ---");
        service.vote(bob.getUserId(), alice_question.getId(), VoteType.UPVOTE); // Bob upvotes Alice's question
        service.vote(bob.getUserId(), answer2.getId(), VoteType.UPVOTE); // Bob upvotes Charlie's answer
        service.vote(alice.getUserId(), answer1.getId(), VoteType.DOWNVOTE); // Alice downvotes Bob's answer

        System.out.println("all answers: " + alice_question.getAnswers());
        System.out.println("votes on question: "+alice_question.getVoteCount());
        System.out.println("vote on bob answer: "+answer1.getVoteCount());
        System.out.println("vote on charlie answer: "+answer2.getVoteCount());
    }
}
