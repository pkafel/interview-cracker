# Rate Limiter

## Fixed Window Rate Limiter

A fixed window rate limiter is a technique used in computer science and software engineering to control the rate at which requests or actions are allowed within a specified time window. It restricts the number of requests or actions that can be performed within a fixed period, such as a second, minute, or hour.

Fixed window rate limiting has some advantages, such as simplicity and predictability. However, it can lead to bursty traffic if multiple requests are made right after the time window resets.

This repository contains 2 implementation which can help you better understand how the rate limiter works.
* [`FixedWindowRateLimiterSimple`](/src/main/java/com/piotrkafel/rate/limiter/FixedWindowRateLimiterSimple.java) - The simplest possible implementation of fixed window rate limiter.
* [`FixedWindowRateLimiterDifferent`](/src/main/java/com/piotrkafel/rate/limiter/FixedWindowRateLimiterDifferent.java) - Implementation that aims to minimize the size of synchronization scope.

## Sliding window rate limiter

A sliding window rate limiter is a technique used in computer systems to control the rate at which requests or actions are allowed within a specified time window. It is similar to a fixed window rate limiter but offers more flexibility and smoother rate limiting.

The key advantage of a sliding window rate limiter is that it provides a smoother rate limiting mechanism compared to the fixed window approach. It can handle bursts of traffic more gracefully and ensures that requests are evenly distributed over time. This is particularly useful in scenarios where you want to prevent sudden spikes in traffic from overwhelming a system.

## Interview context

Rate limiter is a topic that often shows up on interview questions. Companies like Atlassian ask candidates to implement rate limiters during coding interview. The implementation here are good enough that they should pass them.

Beside implementation, interviewers often want to discuss relevant topics during interview. Here is a list of things you need to be prepared for:
* Monotonic vs non-monotonic clocks
* Threads synchronization and different type of locks
* Thread-safe data structures
* Testing logic that depends on time
* Details about how HashMap works

Good luck and keep on rocking folks!
