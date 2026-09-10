package StackOverflow.src;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final String userId;
    private final String name;
    private final AtomicInteger reputation;

    public User(String name){
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.reputation = new AtomicInteger(0);
    }

    public void updateReputation(int change){
        this.reputation.addAndGet(change);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getReputation() {
        return reputation;
    }
    
}
