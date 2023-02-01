# Assignment

An assignment for class **MET CS 579** at Boston University, instructed by **Jae Lee**. Student [Jin Yu Zhang](siege@bu.edu) submitted on **February 1, 2023**.

## Problem 1

**In a database, we don't want any redundancy. However, sometimes we intentionally keep (or introduce) redundancy in a database. What is the purpose of this?**

There are a few possible purposes of it. First of all, we can make the application uses the database faster by storing results from heavy queries in advance in some columns and use it as caches. Second, we can reduce or prevent data corruption because we have duplicated data to check if it is valid. Third, when we receive data from multiple source and combine that data in a rationalized consumption layer, we will want to keep the duplicated data to ensure the provenance of the data.

## Problem 2

**Describe the difference between database schema and database instance.**

The following are from slides 2-7 and 2-8:

- **Database Schema**: The description of a database. Includes descriptions of the database structure, data types, and the constraints on the database
- **Database Instance**: In the slides, database instance is equivalent to database state. The actual data stored in a database at a particular moment in time. This includes the collection of all the data in the database. Also called database instance (or occurrence or snapshot)

A database schema is a blueprint or a description of the structure of a database. It defines the tables, fields, relationships, and constraints that make up the database. The schema defines the structure of the data and the rules for organizing and storing the data in the database.

A database instance, on the other hand, is a single and unique copy of the database created from the database schema. It is a running version of the database that contains the actual data and can be manipulated, updated and queried by the users. A database schema can have multiple instances, each with its own set of data and each serving different purposes or users.

In short, the database schema defines what the database is, while the database instance is what the database contains.

## Problem 3

**What does “a database is in a valid state” mean?**
