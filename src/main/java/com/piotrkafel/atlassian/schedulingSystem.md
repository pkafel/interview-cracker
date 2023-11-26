# Scheduling System

## Clarification
* Whats the time window within which it is acceptable to execute the requests? Is it 1 or 5 minutes? Or do they all need to be executed exactly on Sunday 12:00?
* Whats p99 response time for the service we will be calling?
* What the minimal delay between scheduling request and execution?

## High level design

### Simple approach

```mermaid
---
title: Scheduler Service
---
flowchart LR
writer(Writer Service 1 .. n) --> db[(Database)]
scheduler(Scheduler Service 1 .. n) --> db
scheduler --> queue{{Queue}}
queue --> worker(Workers 1 .. n)
worker --> db
```

### Alternative 1

### Alternative 2


## Writing interface



## Timezone handling



## Handling of crashes and duplication


