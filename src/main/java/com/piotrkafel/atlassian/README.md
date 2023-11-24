# Atlassian interview questions

## Interview

Links on Glassdoor:
* [Atlassian Principal Engineer](https://www.glassdoor.com/Interview/Code-Design-%E2%80%93-Rate-Limiter-Here-is-the-exact-question-I-received-Problem-Title-Rate-Limiter-Problem-Description-Imagine-QTN_6634688.htm)
* [Atlassian Software Engineer](https://www.glassdoor.com/Interview/Rate-limiter-system-design-tagging-QTN_6514165.htm)

### Solution
* Fixed window rate limiter
  * [Simple](./rate/limiter/FixedWindowRateLimiterSimple.java)
  * [Optimized](./rate/limiter/FixedWindowRateLimiterDifferent.java)
* [Sliding window rate limiter](./rate/limiter/SlidingWindowRateLimiter.java)

For more information about how to implement rate limiter please refer to [README here](./rate/limiter/README.md).
