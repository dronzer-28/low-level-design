/**
 * RateLimitingStrategy
 */
public interface RateLimitingStrategy {
    public boolean allowRequest(String userId);
}