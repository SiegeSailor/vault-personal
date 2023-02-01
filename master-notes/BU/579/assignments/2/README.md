# Assignment

An assignment for class **MET CS 579** at Boston University, instructed by **Jae Lee**. Student [Jin Yu Zhang](siege@bu.edu) submitted on **February 1, 2023**.

## Problem 1

**In a database, we don't want any redundancy. However, sometimes we intentionally keep (or introduce) redundancy in a database. What is the purpose of this?**

- **Improved Query Performance**: Introducing redundancy in a database can help to speed up query execution times by reducing the number of joins required to retrieve the desired information. By duplicating certain data, the database can be optimized for specific queries and provide faster response times
- **Data Consistency**: Redundancy can also be used to ensure data consistency and integrity by storing multiple copies of critical data in different locations within the database. This helps to protect against data loss or corruption in the event of a system failure
- **Data Denormalization**: In some cases, it may be necessary to denormalize a database by introducing redundancy in order to simplify the data structure and improve the efficiency of certain types of queries. Denormalization is often used in data warehousing and business intelligence systems where complex queries are run on large data sets. By denormalizing the data, the database can be optimized for fast query execution and improved overall performance

## Problem 2

**Describe the difference between database schema and database instance.**

A database schema is a blueprint or a description of the structure of a database. It defines the tables, fields, relationships, and constraints that make up the database. The schema defines the structure of the data and the rules for organizing and storing the data in the database.

A database instance, on the other hand, is a single and unique copy of the database created from the database schema. It is a running version of the database that contains the actual data and can be manipulated, updated and queried by the users. A database schema can have multiple instances, each with its own set of data and each serving different purposes or users.

In short, the database schema defines what the database is, while the database instance is what the database contains. See the following paragraphs from slides 2-7 and 2-8:

- **Database Schema**: The description of a database. Includes descriptions of the database structure, data types, and the constraints on the database
- **Database Instance**: In the slides, database instance is equivalent to database state. The actual data stored in a database at a particular moment in time. This includes the collection of all the data in the database. Also called database instance (or occurrence or snapshot)

## Problem 3

**What does “a database is in a valid state” mean?**
