import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ShortnerService {
    private static final ShortnerService instance = new ShortnerService();
    private KeyGenerationStrategy generator;
    private String domain;
    private Map<String, ShortUrl> keyToUrl = new ConcurrentHashMap<>();
    private Map<String, String> urlToKey = new ConcurrentHashMap<>();
    private final int MAX_RETRIES = 5;
    private final AtomicLong idCounter = new AtomicLong(1);
    private final List<Observer> observers = new ArrayList<>();
    
    public static ShortnerService getInstance(){
        return instance;
    }

    public void initialize(String domain, KeyGenerationStrategy generator){
        this.domain = domain;
        this.generator = generator;
    }

    public String shorten(String longUrl){
        if(urlToKey.containsKey(longUrl)){
            return domain + urlToKey.get(longUrl);
        }
        String key = generateUniqueKey();

        String raceKey = urlToKey.putIfAbsent(longUrl, key);
        if(raceKey != null){
            return domain + raceKey;
        }
        ShortUrl url = new ShortUrl(longUrl, key);
        keyToUrl.put(key,url);
        notify(url);
        return domain + key;
    }

    public String shortenWithAlias(String longUrl, String alias){
        if(alias.isEmpty() || alias.contains("/")) throw new RuntimeException("Invalid alias: "+alias);
        ShortUrl url = new ShortUrl(longUrl, alias);
        if(keyToUrl.putIfAbsent(alias, url) == null){
            urlToKey.put(longUrl, alias);
            notify(url);
            return domain + alias;
        }
        throw new RuntimeException("Alias already taken: "+ alias);
    }

    private String generateUniqueKey(){
        for(int i=0;i<MAX_RETRIES;i++){
            String checkKey = generator.generateKey(idCounter.getAndIncrement());
            if(!keyToUrl.containsKey(checkKey)){
                return checkKey;
            }
        }
        throw new RuntimeException("Failed to generate unique short key after " + MAX_RETRIES + " tries");
    }

    public Optional<String> resolve(String shortUrl){
        if(!shortUrl.startsWith(domain)){
            return Optional.empty();
        }
        String key = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        if(keyToUrl.containsKey(key)) {
            ShortUrl url = keyToUrl.get(key);
            if(url.isExpired()){
                return Optional.empty();
            }
            notify(url);
            return Optional.of(url.getLongUrl());
        }
        return Optional.empty();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notify(ShortUrl shortUrl){
        for(Observer observer: observers){
            observer.update(shortUrl);
        }
    }
}
