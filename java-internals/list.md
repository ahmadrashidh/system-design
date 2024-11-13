# List

- An ordered Collection

## Implementations

1. ArrayList
   - Better performing
2. LinkedList
   - Better performer than ArrayList under certain circumstances

## Operations

1. Positional Access 
   - `get()`,`set()` returns the value.
   - `add()`,`addAll()` - appends to end
   - `remove()` removes first occurrence of the element and returns it.
2. Search - `indexOf()`, `lastIndexOf()`
3. Iteration - `listIterator()` takes advantage of list's sequential nature
4. Range-View - used to arbitrary range operations on the list
   - `sublist()` contains the specified number of elements from the end of the deck.

## Algorithms

1. `sort` - sort using Merge Sort algorithm to stable sort (i.e. that doesn't reorder equal elements)
2. `shuffle`
3. `reverse`
4. `rotate` - rotate the elements in the specified distance
5. `swap`
6. `replaceAll` - replace all occurrences of a specified value with another
7. `fill` - overwrites every element in the list with specified value
8. `copy`
9. `binarySearch` - searches for an element using Binary Search Algorithms
