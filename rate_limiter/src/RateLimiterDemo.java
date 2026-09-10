public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        String userId = "u1";

        RateLimitingStrategy fixedWindow = new FixedWindowStrategy(10, 10000);
        RateLimiterService service = RateLimiterService.getInstance();
        service.setStrategy(fixedWindow);

        for (int i = 0; i < 20; i++) {
            service.handleRequest(userId);
            Thread.sleep(800);
        }
    }
}
