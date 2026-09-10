public class RateLimiterService {
    private static final RateLimiterService instance = new RateLimiterService();
    private RateLimitingStrategy activeStrategy;

    private RateLimiterService(){}

    public static RateLimiterService getInstance(){
        return instance;
    }
    public void setStrategy(RateLimitingStrategy strategy){
        this.activeStrategy = strategy;
    }

    public boolean handleRequest(String userId){
        if(!activeStrategy.allowRequest(userId)){
            System.out.println("Rate limit exceeded for user: "+ userId);
            return false;
        }
        System.out.println("Request from user " + userId + " is allowed");
        return true;
    }

}
