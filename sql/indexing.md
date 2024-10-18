# Index

- Data is stored in disk, while program is stored in memory
- CPU works only on data in memory
- Retreiving data from disk is 80x slower than that from even SSD memory 
- A data of SQL table can be considered stored as blocks in memory
- Each block will be retrieved sequentially to find the queried data
- Fetching unnecessary blocks are waste of performance
- Database performs optimisation to  make queries fast

## Index
- Indexes(like index in book) helps findings blocks containing queried data faster
- Indexes reduces number of block access to find queried data

## How indexes work?

- Index table cannot be Hashmap as it doesn't work for range queries
- Index works very similarly to Treemap
- Indexes use data structure called B Tree or B+ Tree

**Pros of B Tree in Indexing**
- Height of B Tree is less as node has many children
- All operations are also relatively faster for the same reason.
- Time complexity of operations are in the order of the height of the B Tree


## Cons of Indexing

- Write operations are slower as write operations like CUD updates the index (as well as rebalancing the tree  etc).
- Copy of index is stored in disk which further slowdown write operation and also increases storage requirement.


## NOTE
- Don't create index prematurely.
- Observe performance metrics of queries.
- 'New Relic' dashboard to note frequency of the query

## Indexing
- When you created an index on name, Tree map is sorted by name.
- If you have an index on (name, psp) it won't help only on psp.

## Indexing on strings

- Size of index will be huge
- String matching is slower
- For example for email string, typically, index is create for prefix value.
- For other strings, find a sweet spot of number of characters to be indexed that reduces number of block accesses. 

**Pattern Matching**
- Indexing on strings doesn't help pattern-matching (i.e. `like` clause)
- Refer `FULL-TEXT-INDEX`

creating index for title and release year in film table
```sql
create index idx_film_title_year on film(title, release_year)
```


To check whether an query `select * from film where title = "ADAPTATION FILES"` uses specified index
```sql
explain analyze select * from film where title = "ADAPTATION FILES"
```