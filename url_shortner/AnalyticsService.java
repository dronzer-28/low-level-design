import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AnalyticsService implements  Observer{
    private final Map<String, AtomicLong> clickCounts = new ConcurrentHashMap<>();

    @Override
    public void update(ShortUrl url) {
        String shortKey = url.getShortUrl();
        if(clickCounts.putIfAbsent(shortKey, new AtomicLong(0)) == null){
            System.out.printf("[Analytics] URL created: Key=%s, Original=%s%n", shortKey, url.getLongUrl());
        }
        else{
            AtomicLong count = clickCounts.get(shortKey);
            count.incrementAndGet();
            System.out.println("[Analytics] URL accessed: Key="+shortKey+", Clicks="+count);
        }
    }
    
}
