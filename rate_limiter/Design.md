# Requirements

Q. per user basis or IP or API key
Q. which rate limiting algorithm
Q. what should be the rate limit and is it configurable per user?
Q. what happens when a user hits rate limit

- support rate limiting on per user basis
- support fixed window and token bucket algorithm
- same limit for all users. 100 requests per 60 seconds for fixed window, and capacity 100 with refill rate tokens per second for token bucket
- return boolean for request allowed or denied
- thread safe implementation

# Core Entities

RateLimitingStrategy
User
RateLimiterService

# Class Design and Relationships

interface RateLimitingStategy{
    + allowRequest(userId: String) -> boolean
}

class FixedWindowStrategy{
    - nRequests: int
    - window: long
    - users: Map<String, UserRequestInfo>

    + FixedWindowStrategy(nRequests: int, window: int)
    + allowRequst(userId: String) -> boolean
}

class TokenBucketStrategy{
    - capacity: int
    - refillRatePerSec: int
    - users: Map<String, TokenBucket>

    + TokenBucketStrategy(capacity: int, refillRatePerSec: int)
    + allowRequst(userId: String) -> boolean
}

class RateLimiterService{
    - activeStrategy: RateLimitingStrategy
    - instance: RateLimiterSerivice

    - RateLimiterService()
    + handleRequest(userId: String) -> boolean
    + setActiveStrategy(strategy: RateLimitingStrategy) -> void
    + getInstance() -> RateLimitingStrategy
}

class UserRequestInfo{
    - userId: String
    - requestCount: int
    - windowStart: long

    + reset() -> void
}

class TokenBucket{
    - userId: String
    - capacity: int
    - tokens: int 
    - refillRatePerSec: int
    - lastRefillTime: long

    + refill(currentTime: long) -> void
}