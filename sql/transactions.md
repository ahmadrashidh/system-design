# Transactions
> a set of database operations logically grouped to complete a task

## Properties of Transaction

1. ACID

### ACID Properties

#### A - Atomicity
- Transaction should appear indivisible to an end user
- Transaction never end with intermediary result - either it happened or it never happened

#### C - Consistency

- Correct and exact across all time and system
- State of database before and after transaction should be accurate. 
#### I - Isolation
- One transaction shouldn't affect another transaction if it is happening at same time on same data.

#### D - Durability

Outcome of the transaction should persist.

start transaction
```sql
start transaction
```

## Commit

`Commit` - persist the result of an SQL query to disk

Commit satisfies `Durability` property

```sql
commit;
```

### Autocommit
Every SQL query starts transaction, executes and commit itself.

- By default, `autocommit` is set to true for MySQL Editor session which means every execution is new transaction

set auto committing false
```sql
set autocommit = 0;
```
# Rollback

`rollback` - undo changes of last commit to previous commit

```sql
rollback;
```
# Isolation

 - In MySQL Editor, each editor session is isolated.
 - Isolation is similar to working on one copy of data
 - There are many levels of isolation based on similarity set exclusive to respective session.

get isolation level parameter
```sql
show variables like 'transaction_isolation'
```

set isolation level
```sql
set session transaction isolation level read uncommitted 
```

 ## Isolation Levels
 (in increasing order of severity)

 1. Read Uncommitted
 2. Read committed (PostgreSQL's default )
 3. Repeatable read (MySQL's default)
 4. Dirty Read
 5. Serializable

### Read Uncommitted
> Transaction reads even uncommitted data that is from another transaction
 - caused problem `dirty read`

### Read committeed
> Transaction only reads latest committed data
  - caused problem `non-repeatable read`

### Repeatable Read
> Transaction reads a data from snapshot of last read operation (after transaction started) 

- only guarantees that rows that has been read remains same for the whole transaction.
- no guarantees about complete table
- `Phantom read` - is a phenomenon in `repeatable read` that row that is never read appears or intended to appear.

### Serializable
> 

 ## Locking

### Shared Lock
- When row is read by transaction, `shared lock` is applied.
- When row is `shared locked`, other transaction can read and write on that row.


### Exclusive lock
- When a row is written by transaction, `exclusive lock` is applied on that.
- When row is `exclusively locked`, other transaction cannot write on that row.

