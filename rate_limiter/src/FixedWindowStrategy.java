import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitingStrategy{
    private final int maxRequests;
    private final long window;
    private final Map<String, UserRequestInfo> usersRequestInfo = new ConcurrentHashMap<>();

    public FixedWindowStrategy(int maxRequests, long window){
        this.maxRequests = maxRequests;
        this.window = window;
    }

    @Override
    public boolean allowRequest(String userId) {
        long curTime = System.currentTimeMillis();
        UserRequestInfo info = usersRequestInfo.computeIfAbsent(userId, k -> new UserRequestInfo(curTime));
        synchronized(info){
            if(curTime - info.windowStart >= window){
                info.reset(curTime);
            }
            if(maxRequests > info.requestCount){
                info.requestCount++;
                return true;
            } else
                return false;
        }
    }
    
    private static class UserRequestInfo {
        long windowStart;
        int requestCount;

        UserRequestInfo(long startTime) {
            this.windowStart = startTime;
            this.requestCount = 0;
        }

        void reset(long newStart){
            requestCount = 0;
            windowStart = newStart;
        } 
    }
}
