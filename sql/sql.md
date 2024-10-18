# SQL - Structured Query Language
> language to query on organized data

mysql command as well as string is case-insensitive, so

```sql
select "sana" = "SaNa"; -- returns 1(true)
```

## CREATE

```sql
create database sample;
```
When you create the database, mysql rightaway make it default database

```sql
use sample;
```
makes database as default


### CREATE statement

```sql
create table students (
    id int auto_increment,
    firstname varchar(50) not null,
    lastname varchar(50),
    email varchar(100) not null default 'abc@gmail.com',
    dateOfBirth date,
    enrolmentDate timestamp not null,
    psp decimal(3,2),
    batchId int,
    isActive boolean not null,
    primary key (id),
    foreign key (batchId) reference batches(id),
    on delete cascade,
    on update cascade
);
```

**Constraints**
`not null` - required
`unique` - no two rows should have the same value on that column
`default` - when value is not specified, use the provided default value
`auto_increment` - automatically increment value based last entry
`on delete cascade` - if entry in foreign table is deleted, all of the entries linked to that entry is deleted accordingly.
`on update cascade` - if entry in foreign table is updated, all of the entries linked to that entry is updated accordingly.

### INSERT statement
> insert row into table

```sql
insert into table_name (column_names) values (respective_values),(respective_values_of_another_entity);
```

- specifying column names less prone to errors, less tedious to write.
- to avoid value for column, leave it out in column names/ use `default` if it has default value/use `null` if its nullable

import values of one table to another table
```sql
insert into copied_table (column_names) select respective_column_names from original_table
```
- column_names and respective_column_names should have respective datatypes same.

## READ

### SELECT statement
> reads data from table

select all rows from table
```sql
select * from table_name
```

select specific columns from table
`*` - all rows

```sql
select column_name,another_column_name from table_name
```

select column names with alias name from table
```sql
select column_name as alias_name,another_column_name as respective_alias_name from table_name
```
select rows with distinct values from column

```sql
select distinct column_name from table_name
```

select distinct rows from pair of columns
```sql
select distinct column_name, other_column_name from table_name
```
`distinct` should be very first command after `select`

select as print statement
```sql
select 1; -- prints 1

select "Hello World"; -- prints Hello World
```

```sql
select column_name, 'hello' as alias_name from table_name
```
Above query prints values in column and 'Hello' under alias_name


```sql
-- selecting title of the film, duration of film under length(in minutes) column in hours
select title, round(length/60) as duration_in_hrs from film;

-- selecting title of the film, computing how many times renter can watch same movie as value
select title, floor(rental_duration/(length/60)) as times_can_watch from film
```

#### WHERE clause


```sql
select column_names from film where condition;

select title from film where rating == 'PG13';
```
### Operations in select query

#### AND/OR/NOT

get films released in year of 2006 with rating of PG13.
```sql
select title from film where rating = 'PG13' and release_year = 2006;
```

get films not released in year of 2006 with rating other than PG13
```sql
select title from film where (not rating = 'PG13') and (not release_year = 2006);
```

- always use paranthesis when using multiple conditions
- comparison operators are ==, !=, <>, <, >, <=, >=

#### NOT EQUAL

get films not released in year of 2006 with rating other than PG13

```sql
select title from film where (rating != 'PG13') and (release_year <> 2006);
```

#### IN

get all the students of batches 1,5,6,9
```sql
select * from where batchId = 1 or batchId = 5 or batchId = 6, batchId = 9
```

can be simply written with `in` operator


```sql
select * from where batchId in (1,5,6,9)
```

#### BETWEEN

get all the students with psp 50 and 70
```sql
select * from students where (psp >= 50) and (psp <= 70);
```

can be simply written with `between` operator

```sql
select * from students where psp between 50 and 70 -- inclusive
```

#### LIKE
> used to filter by string in value of a column using wildcard symbols

**Wildcard symbols**
`_` - exactly one occurrence of a character
`%` - any number of occurrences of any character 


get films whose title contains word love
```sql
select * from film
where title like '%love%'
```

get batch which academic and in morning
```sql
select * from batch
where name like %academic% and name like '%morning%'
```

get batch whose id contains 123 in the middle, total length of id is 5
```sql
select * from batch
where id like '_123_'
```
- everything is case insensitive

#### IS NULL

- can't check `NULL` using equality/comparison operator

get the films where description is null
```sql
select * from film where description is null
```
get the films where original_language_id is not null
```sql
select * from film where original_language_id is not null
```
get the students where batch_id is not 2
```sql
select * from students where batchId <> 2 -- this doesn't print those with null values
```
if you want null values
```sql

select * from students where batchId <> 2 or batchId is null
```

#### ORDER BY
> sort the final answer

- By default, mysql query result is ordered ascending by primary key

get films order by title in ascending
```sql
select * from film order by title asc;
```

  
get films descending order by title and release_year
```sql
select * from film order by title,release_year desc;
```

get films ascending ordered by title , descending ordered by release_year
```sql
select * from film order by title asc, release_year desc;
```

- can only be ordered by column applied on distinct clause
- can only be ordered by column present in the select clause
 

§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§
 WHY ORDERBY HAS TO BE SAME COLUMN AS DISTINCT
 ¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢


#### LIMIT
> limit number of rows to be returned

- added at the end of the query
- returns first set of data as per size of limit
```sql
select * from film limit 100
```

`offset` - first number of data to be skipped
```sql
select * from film limit 100 offset 10
```



## Update

> modifies value in the column/set of columns

### UPDATE statement
```sql
update table_name set column_name = new_value where condition
```

- if you miss where clause, it will update all rows in the table

updating psp of the student id is 3002
```sql
update students
set psp = 30 
where id = 3002
```

## Delete


### DELETE statement
> delete few rows one by oneof the table


```sql
delete from table_name where condition
```
- if you miss the where condition, all rows in the table will be deleted

### TRUNCATE statement
> delete all of the rows at once in the table

```sql
truncate table_name;
```
- no `where` clause
- similar outcome as `delete from table_name`
- faster to delete than `delete` statement because it removes older table and creates new table instead of deleting row by row.
- resets the key
- cannot be rolled back.


### DROP statement
> deletes the table

## JOINS
> combines data across multiple tables

print the name of every student with name of the batch
```sql
select students.name, batches.name
from students
join batches
on students.batchId = batches.id
```
- not necessary to join only on equality foreign key/ primary key

```sql
select students.name, batches.name
from students
join batches
on length(students.name) = length(batches.name)
```

- can give alias name to table for readability
```sql
select f.title, l.name
from film f
join lang l
on f.languageId = l.id;
```


### Self Join
> combine every row of a table with itself

assigning buddies for every student from the students itself
```sql
select s.name, b.name
from students s
join students b
on s.buddyId = b.buddyId
```
- aliases are mandatory for self join

### Multiple Join

- join multiple tables like adding multiple number i.e. do it in pairs.

for every student, give the name of instructor and the batch
```sql
select * from students
join instructors i
on s.instructorId = i.id
join batches b
on s.batchId = b.id
```

### Compound Join
>  joining tables with conditions


for every film, name all of the film that were released within 3 years of that film and their rental rate is greater than that of movie
```sql
select * from film f1
join film f2
on between f1.year-2 and f1.year+2
and f2.rental > f1.rental
```

- needn't be always equality of column
- can also have multiple conditions on different columns

### Type of Joins


#### INNER JOIN
> Join that results only rows of both tables that match

for example, if there are nulls in the batchId column, or some of the rows from right unused in the join - you can use inner join
```sql
select * from s.name, b.name
from students s
join batchs b
on s.batchId = b.id
```
- `inner` is optional

#### OUTER JOIN
> Join that also allows rows of both tables that doesn't match

##### Types

1. LEFT JOIN or LEFT OUTER JOIN
   - includes all rows that matches
   - includs all rows of the left table that doesn't match, and its RHS is filled with NULL
2. RIGHT JOIN or RIGHT OUTER JOIN
   - includes all rows that matches
   - includs all rows of the right table that doesn't match, and its LHS is filled with NULL 
3. FULL JOIN or FULL OUTER JOIN
   - includes all rows that matches
   - includs all rows of the left table that doesn't match, and its RHS is filled with NULL
   - includs all rows of the right table that doesn't match, and its LHS is filled with NULL 

#### CROSS JOIN
> join every row in the left table with every row in right table

map every show with every seat type
```sql
select *
from shows
cross join seatTypes
```

## USING
> join based on equality on comparing column names are same

```sql
select * 
from film_actor
join film
on film.film_id = film_actor.film_id
-- can be written as
select *
from film_actor
join film
using (film_id)
```

if comparison is between multiple columns
```sql
select * 
from film_actor
join film
on film.film_id = film_actor.film_id and  film.actor_id = film_actor.actor_id
-- can be written as
select *
from film_actor
join film
using (film_id,actor_id)
```

## NATURAL JOIN
> join based on equality of all columns with same names 

```sql
select *
from A
join B
on A.a = B.a and A.b = B.b ...etc
-- can be written as
select *
from A
natural join B
```

## IMPLICIT JOIN
> `where` clause used to join two tables

```sql
select *
from students, batchs
where students.id = batchs.id
```

- implicit join is cross join but high time and space complexity 

# Aggregate Functions
- All aggregation functions in SQL ignore `null` values

## COUNT

fetches lot of values and combines into single value which is count of non-null values in the result set.

Give count of students that have batch.
```sql
select count(batchId)
from students
```
get count of students with batch name as A

```sql
select count(s.id) 
from students s
join batchs b
on s.batchId = b.id
where b.name = 'A'
```
`select count(1)` or `select count('Shubham')` is equal to `select count(*)`


## GROUP BY
> breaks the result table into multiple groups so as to be used by aggregate function

get all students grouped by batch_id
```sql
select count(*), batchId
from students
group by batchId;
```
- you can groupby the column that is selected in the result

get all films grouped by rating, release_year
```sql
select count(1), rating, release_year
from films
group by rating, release_year;
```

## HAVING
> allows to do filtering on groups


print the batch names along with count of students that has more than 100 students
```sql
select count(s.id), b.name
from students s
join batchs b
on s.batchId = b.id
group by b.name
having count(s.id) > 100
```
