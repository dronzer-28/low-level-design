import java.time.LocalDateTime;

public class ShortUrl {
    private final String longUrl;
    private final String shortUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiry;

    public ShortUrl(String longUrl, String shortUrl, LocalDateTime expiry) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdAt = LocalDateTime.now();
        this.expiry = expiry;
    } 

    public ShortUrl(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdAt = LocalDateTime.now();
        this.expiry = null;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isExpired() {
        return expiry != null && LocalDateTime.now().isAfter(expiry);
    } 
}
