# Subqueries
> intuitive way to write queries

- `IN`
- `ALL`
- `ANY`
- Correlated sub-queries
- `EXISTS`
- subqueries using `FROM`, `WHERE`

get all students whose psp is greater than the psp of student 18

it can be written using `JOIN`
```sql
SELECT *
FROM students s
JOIN students p
ON p.id = 18 and s.psp > p.psp
```

- Subqueries are written by breaking problem into smaller problem, and combining result for complete solution.
- Subqueries are always enclosed in parenthesis

with subqueries it can be written as
```sql
SELECT *
FROM students
WHERE psp > (SELECT psp 
FROM students 
WHERE id=18)
```

###  Tradeoff of Subqueries 
- very bad performance

get all years where the average of the rental_rate of films of that year was greater than the global average of rental rate(average of all films)
```sql
-- 1. Find global average
SELECT avg(rental_rate)
FROM film;

-- 2. Find average of the year
SELECT release_year, avg(rental_rate)
FROM film
GROUP BY release_year;

-- Get filtered groups
SELECT release_year, avg(rental_rate)
FROM film
GROUP BY release_year
HAVING avg(rental_rate) > (SELECT avg(rental_rate)
FROM film);
```

## IN

get names of those students whose name is same as TA

using `JOIN`
```sql
SELECT DISTINCT s.name
FROM users s
JOIN users t
ON s.is_student = true 
AND t.is_ta = true 
AND s.name = t.name
```

using subquerying
```sql
-- 1. Get all TAs names
SELECT DISTINCT name
FROM users u
WHERE u.is_ta = true

-- 2. Combined Result
SELECT DISTINCT name
FROM users s
WHERE s.is_student = true
AND s.name IN (SELECT DISTINCT name
FROM users u
WHERE u.is_ta = true)
```

## ALL
> compares the left hand side with right hand side, if all of them return true, `ALL` returns true

Get all the students whose psp is greater than smallest psp of every batch
```sql
SELECT *
FROM students
WHERE psp > (SELECT max(psp) 
FROM (SELECT min(psp)
FROM students
GROUP BY batch_id
)minpsps
);
```
- Output of subqueries is considered like table in itself upon which you can write another query
- You should always name the same query

can be written using `ALL` as
```sql
SELECT *
FROM students
WHERE psp > ALL (SELECT min(psp)
FROM students
GROUP BY batch_id
)
```

## ANY
> compares the left hand side with right hand side, if any of them return true, `ANY` returns true

Get all the students whose psp is greater than smallest psp of any one batch
```sql
SELECT *
FROM students
WHERE psp > ANY (SELECT min(psp)
FROM students
GROUP BY batch_id
)
```

## Correlated Subqueries
> subqueries that uses variable from the parent query

get all students whose psp is greater than average psp of batch 1
```sql
SELECT *
FROM students s
WHERE psp > (SELECT avg(psp)
FROM students
WHERE batch_id = s.batch_id)
```
- `s.batch_id` in the subquery is referring to `students` in parent query
- subqueries are not allowed as part of join in some SQLs

## EXISTS

get all the students who are also TAs (apart from student table, there is TA table where in student_id column is null, if that TA has never been student)

```sql
SELECT student_id
FROM TA
where student_id IS NOT NULL
```
```sql
SELECT *
FROM students s
WHERE id IN (SELECT student_id
FROM TA
WHERE student_id IS NOT NULL)
```

```sql
SELECT *
FROM students s
WHERE id EXISTS (SELECT student_id
FROM TA
WHERE student_id = s.id )
```

here `IN` can be replaced `EXISTS`,
for every row of student
- it will run subquery
- if subquery returns any one row, it returns true.

- in `IN` clause, the subquery is run first to fetch all rows in the table which is time and space expensive. `EXISTS` is alternative.