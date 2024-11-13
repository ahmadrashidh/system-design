# Queue
> Collection for holding elements prior to processing

- Queues typically, but not necessarily, order elements in a FIFO (first-in-first-out) manner.
- Among the exceptions are priority queues, which order elements according to their values
- Some Queue in java.util.concurrent are bounded/size-restricted, but the implementations in java.util are not.

## Queue Operations

Each Queue method exists in two forms: 
1. throws an exception if the operation fails, and 
2. other returns a special value if the operation fails (either null or false, depending on the operation)

| Operation  | Throws Exception | Returns Special Value |
|------------|------------------|-----------------------|
| Insertion  | add(e)           | offer(e)              |
| Removal    | remove()         | poll()                |
| Examine | element()        | peek()                |


- Insertion operations add element at the tail of the Queue. `add()` in bounded Queue, throws `IllegalStateException` if it exceeds size.
- Removal operations is done at front element aka `head` of the Queue. `remove` method throws `NoSuchElementException` if its empty.
- Examine operation returns `head` of the queue but not remove that.


