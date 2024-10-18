# Schema Design
> Desiging structure of a database

## Steps to create Schema Design

1. Define tables
   - Find all nouns, for each noun wonder whether we need to store the data about that entity
2. Create tables
   - Add all attributes about that entity.
   - Defining primary key. 
     - If primary key changes, we need to do expensive operations like update index and re-sort the data on disk.
     - Primary key should be simplest and longest datatype (preferably biggest integer)as SQL sorts the data based on it and database can grow bigger.
     - name it `id` or `tablename_id`(easy to use `using`)
3. Represent relationships (Cardinality)
## Convention
> Varies  by company development  policy

- Name of the table should be plural in snake case/ pascal case.
- Name of the column in snake case

## Cardinality
> each entity is related to how many of relating entity

- Given two entities, understand the function(relationship) between them to define cardinality

### Possibilities
#### 1:1 
- Example: One student in one batch
- Put id of left table into right table. Apply `JOIN` when you want to retrieve other relationship. Trade-off: performance. Ideal practice
- Put id of left table into right table and ice versa.Trade-off - redundancy causes inconsistency

#### 1:M
- Example: One mentor can have many students
- Put it of right table into left table.
  
#### M:1
- Example: Many students will under in one mentor
- Put it of left table into right table.

#### M:M
- Example: One order can have many products. One product can be in many orders.
- Create new table for mapping (ex: Order_Products  with order_id and product_id)

### Sparse Relationship
> In a specific use case, in a related table, lot of entities may not be part of the relation causing wastage of storage.
-  In that scenario, create new table for relationship mapping.
-  Saved space, tradeoff of performance as fetching needs `JOIN`

> In another use case, in a related tables, additional information is needed about the relationship between two entities
- Create new table for mapping with that information

## Example: Scaler Schema Design

1. Scaler has multiple batches. Each batch should store their name, start month, current instructor.
2. Each batch have multiple classes. Each class stores the name of the class, date and time of the class, instructor of the class
3. Each batch has multiple students. For every student, stores their name, grad year, university name, email, phone number and whether it DSML or Academy.
4. 
5. Every student has a buddy, who is also a student
6. A student may move from one batch to another. For each batch student goes to, start date of that batch need to be stored.
7. Every student has a mentor. For every mentor, name and current company is stored.
8. Information about all mentor sessions is stored like time, duration, student, mentor, rating by student, rating by mentor.

### Batches
| batch_id | name | start_month | current_instructor |curr_instructor_id|batch_type_id|
|----------|------|-------------|--------------------|-|-|

### Instructors
| instructor_id | name | email | avg_rating |
|---------------|------|-------|------------|

### Students
| student_id | name | email | phone | grad_year | uni_name |batch_id|buddy_id|
|------------|------|-------|-------|-----------|----------|-|-|

### Classes
| class_id | name | scheduled_time |instructor_id|
|----------|------|----------------|-------------|

### Batch Clases
| class_id | batch_id |
|----------|------|

### Student Batch Transfer
| student_id | batch_id | move_date |
|----------|------|----------------|
### Mentors
| mentor_id | name | company_name |
|----------|------|----------------|

### MentorSessions
| mentor_session_id | time | duration | student_rating | mentor_rating |
|------------|------|-------|-------|-----------|


## Schema Design of Netflix

1. Netflix has users.
2. Every user has an email and a password.
3. Users can create profiles to have separate independent environments.
4. Each profile has a name and a type. Type can be KID or ADULT.
5. There are multiple videos on netflix.
6. For each video, there will be a title, description and a cast.
7. A cast is a list of actors who were a part of the video. 
8. For each actor we need to know their name and list of videos they were a part of.
9. For every video, for any profile who watched that video, we need to know the status (COMPLETED/ IN PROGRESS).
10. For every profile for whom a video is in progress, we want to know their last watch timestamp.


