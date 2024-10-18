# DBMS - Database Management System

## Limitations of storing data in text files

- Inefficient storage and retrieval
- Data corruption 
- Inconcurrent
- Insecure
  - data is stored in disk
  - anyone having access to file, can do anything with file


## DBMS 
> software system to manage database efficiently and securely with concurrency and data integrity,.

**Database** - collection of data of related thing


### Type of Databases

#### Relational Database
> stores data in multiple related tables

- Every table may be an entity or relation between entities
- Every row should be unique
- Name of every column should be unique
- All values of a column should have same datatype
- Each value in a column is atomic
  - i.e. cannot be lists/dictionary/maps/json etc
  - though many SQL DB allows this, they are not inefficient i.e. workarounds
- Order of columns is not guaranteed
  - most SQL DB order columns as during creation
- Order of rows is also not guaranteed
  - most SQL DB return in the order of primary key
  - if you want to be in order, specify it.

#### Non-Relational Database
> stores data in any form other than tables

### Keys
> identifies a row uniquely

**Super Key** - column or group of columns whose value is guaranteed to uniquely identify a row.
**Candidate Keys** - minimal superkey - set of columns from which if you remove one, it will not idenfify a row uniquely.
**Primary Keys** - One of the candidate keys by which database will be sorted on the disk.
  - only one primary key
  - value is always required
  - value of primary key should never change
  - integer is efficient to sort, than string
  - sometimes, none of the candidate keys satisfy above property, so we end up creating ID for it i.e. UUID

**Foreign Keys** - maps column/set of columns from one table to column/set of columns to another table.
  - both the side can be nullable.
  - columns of referred table(other table) must be unique.
  - avoids possible errors like orphaning on manual mapping.


 

