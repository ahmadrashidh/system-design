# Collections

- A `collection` represents groups of objects that are naturally similar.

## Benefits
- Reduced programming effort
- Increased speed and quality
- Interoperability

## Collection Interface
> Used to pass around different collections of objects - *abstract datatype*

- Collection
  - Set
    - SortedSet
  - List
  - Queue
  - Dequeue

- Map
  - SortedMap


- `Collection` interface doesn't not provide implementations. Specific implementations such as List, Set, etc implement `Collection` interface to have generality.
- Those implementions are generic, as `Collection` is declared as:
    ```java
    public interface Collection<E>{}
    ```
- `Map` is not a true `Collections`
- By convention, all collection implementations have constructor that takes `Collection` type argument - known as *conversion constructor*
- `Collection` interface contains methods that does 
  - basic operations on elements in collection such as `size()`, `isEmpty()`, `contains()`, `add()`, `remove`
  - operations on entire collections such as `containsAll()`, `addAll()`, `removeAll()`, `retainAll()`, `clear()`.
  - array operations such as `toArray()`
- Some implementation throws `UnsupportedOperationException` if it doesn't support any `Collection` operation - it is not required to.
- Some implemetations throw `NullPointerException` or `ClassCastException` while some return `false` if an ineligible elements are passed to for operation of the Collection

## Traversing Collections

1. Aggregate operations like `stream()` in JDK8 and later
2. For-each
3. Iterator

Use Iterator instead of the for-each construct when you need to:
  - Remove the current element. 
  - Iterate over multiple collections in parallel.


## Object Ordering

Collection Implementations and by what it is ordered when `sort` is called
![Collection Implementations and by what it is ordered when `sort` is called](<Screenshot 2024-11-05 at 20.51.42.png>)

