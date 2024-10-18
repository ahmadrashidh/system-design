# Synchronisation


## Adder Subtractor Problem
> Running adder function and subtractor function in different threads.

| Adder    | Subtractor |
|----------|------------|
| X=READ() | X=READ()   |
| X=X+1    | X=X-1      |
| WRITE(X) | WRITE(X)   |

### Problem
> Inconsistent result due to execution of two independent threads on same data.

**Critical Section**: section of code where shared resource is accessed
**Race Condition**: phenomenon of more than one thread want to accesssing critical section at the same time.
In multithreaded system, critical section and race condition together cause synchronisation problem.

**Pre-emption**: Context-switching between threads in single-threaded system. 

- Not allowing pre-emption when execution is accesssing critical section can avoid the synchronisation problem.

### Solution

**Mutual Exclusion(mutex)** - only allow one thread to enter `critical section` at single time.

**Mutex Lock**: Only one thread can have lock, other thread has to wait. A thread can only enter `critical section` if it has lock.

| Adder    | Subtractor |
|----------|------------|
| X=READ() | WAIT()     |
| LOCK()   | WAIT()     |
| X=X+1    | WAIT()     |
| WRITE(X) | WAIT()     |
| UNLOCK() | READ()     |
| -        | X=X-1      |
| -        | WRITE(X)   |
|   -      | UNLOCK()   |


```java
lock.lock();
int value = this.count.getValue();
value = value - i;
this.count.setValue(value);
lock.unlock();
```

## Synchronised

**Problem**: The waiting threads need to poll to know that the critical section is unlocked which is CPU intensive known as `busy-waiting`

- The busy-waiting threads are queued when critical sections is locked
- Once the critical section is unlocked, the waiting thread will be popped from queue and assigned lock.


### Synchronised keyword
> Implicit lock

Whenever block of code is tagged as synchronised, there will lock before it and unlock at the end to release it.

**Block Level:**
```java

synchronized(count) {
    int value = count.getValue();
    int nextValue = value + i;
    count.setValue();
}

```

`count` here is known as `monitor_variable` checks if they are same instance to lock the section [Baeldung - Monitor](https://www.baeldung.com/cs/monitor)

**Method Level**:
```java
public synchronised void incrementCount(int offset){
    this.value += offset;
}
```
method defined `synchronised` can only be called by one thread at a time on a same instance.

## Concurrent Datastructure

### HashMap

`Goldmansachs`

| HashMap         | HashTable          | Concurrent HashMap          |
|-----------------|--------------------|-----------------------------|
| Not thread safe | Thread safe        | Thread safe                 |
|                 | Lock the whole map | Lock a bucket of keys       |
| Very fast       | Very slow          | Faster than HashTable       |

### String

| String      | StringBuffer       | StringBuilder             |
|-------------|--------------------|---------------------------|
| Thread safe | Thread safe        | Not thread safe           |
| Very Slow   | Fast               | Faster than String Buffer |

## Producer-Consumer Problem (by Djikstra)
> Synchronisation of products and consumers

- Example of multiple units simultaneously produce products and add it to limited-spaced belt. At the same time, multiple  consumers consume the products in the belt. Belt here is `Bounded Buffer`

**Problem**: Multiple consumer threads race for product when product in belt is less/empty known as 'underflow'. Other multiple units keep producing when products overflows in belt


**Semaphore** - how many threads can access the shared resource. So mutex is `Semaphaore` of 1.


### Using Semaphore

`Lock.lock()` -> `Semaphore.acquire()`
`Lock.unlock()` -> `Semaphore.release()`




## Atomic Datatypes

