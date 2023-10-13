# Stripe

## Description
(copy paste from the link below in case someone will remove it)

>Part-1
Customer log: "Y Y N Y" //represents if customer come to store every hour, Y means come, N means no.
Closing Time: Store is closed at a given hour
So need to compute the penalty by the rule:
> * if customers dont come (N) when the store is open: +1
> * if customers come (Y) when the store is close: +1
int compute_penalty(String log, int closing_time);
//scan input string from start to closing for 'N'; then closing to end for 'Y' to increment the penalty variable.

> Part-2
Given customer log input string, find the closing time with minimum penalty.
int getClosingWithMinPenalty(String log);
// use the part 1 function to compute closing from 0 to end and keep track of minimum penalty

> Part-3
Here the input string will be give for multiple stores like below format:
"BEGIN BEGIN BEGIN Y Y N Y END Y Y N N END Y N Y N END"
List getAllClosing(String log);
// use stack and part-2 function to get all closing time

## My experience
During my interview with Stripe I had an alternative version of part-3. In my version the task was to implement `get_best_closing_times`. The method would take a polluted log and had to scan for valid sequences of opening hours. For eahc valid sequence it would need to calculate best closing time in return them on a list.

Example input: `Y Y Y BEGIN Y Y Y Y BEGIN Y Y \n Y Y Y END Y BEGIN Y Y Y BEGIN \n Y Y Y Y BEGIN Y Y Y END`

### Proof of the question
[Link to Leetcode](https://leetcode.com/discuss/interview-question/2585038/Stripe-or-Phone-Screen-or-Senior-SE-or-Reject)
