# Assignment

An assignment for class **MET CS 579** at Boston University, instructed by **Jae Lee**. Student [Jin Yu Zhang](siege@bu.edu) submitted on **February 1, 2023**.

## Problem 1

**In a database, we don't want any redundancy. However, sometimes we intentionally keep (or introduce) redundancy in a database. What is the purpose of this?**

- **Improved Query Performance**: Introducing redundancy in a database can help to speed up query execution times by reducing the number of joins required to retrieve the desired information as caches. By duplicating certain data, the database can be optimized for specific queries and provide faster response times
- **Data Consistency**: Redundancy can also be used to ensure data consistency and integrity by storing multiple copies of critical data in different locations within the database. This helps to protect against data loss or corruption in the event of a system failure
- **Ensuring Data Provenance**: In some cases, we will receive a data from multiple sources and combine that data in a rationalized consumption layer, which is common in data warehouses. Storing duplicated data is important to ensure the provenance of the data

## Problem 2

**Describe the difference between database schema and database instance.**

A database schema is a blueprint or a description of the structure of a database. It defines the tables, fields, relationships, and constraints that make up the database. The schema defines the structure of the data and the rules for organizing and storing the data in the database.

A database instance, on the other hand, is a single and unique copy of the database created from the database schema. It is a running version of the database that contains the actual data and can be manipulated, updated and queried by the users. A database schema can have multiple instances, each with its own set of data and each serving different purposes or users.

In short, the database schema defines what the database is, while the database instance is what the database contains. See the following paragraphs from slides 2-7 and 2-8:

- **Database Schema**: The description of a database. Includes descriptions of the database structure, data types, and the constraints on the database
- **Database Instance**: In the slides, database instance is equivalent to database state. The actual data stored in a database at a particular moment in time. This includes the collection of all the data in the database. Also called database instance (or occurrence or snapshot)

## Problem 3

**What does _a database is in a valid state_ mean?**

A database is said to be in a _valid state_ when it meets certain conditions or constraints that have been defined for the data stored in it. This may or may not include conditions such as the data being complete and accurate, meeting specific business rules and regulations, and being properly formatted and organized. A valid state is important because it helps to ensure that the data stored in the database can be trusted and used for accurate decision making. When a database is in a valid state, it provides confidence to its users that the data is correct, consistent and can be relied upon for critical business operations. See the following paragraphs from slide 2-9: A state that satisfies the structure and constraints of the database.

## Problem 4

**Consider the ER schema diagram of the AIRLINE database in Figure 3.21 (page 98):**

![Figure 3.21](./Figure%203.21.png)

**(1). List all multi-valued attributes. If there is none, then write "none".**

None.

**(2). List all composite attributes. If there is none, then write "none".**

None.

**(3). List all derived attributes. If there is none, then write "none".**

None.

**(4). List all weak entity types. For each weak entity type, show the owner entity type and the identifying relationship type.**

- **LEG_INSTANCE**
  - Owner entity type: FLIGHT_LEG
  - Identifying relationship type: INSTANCE_OF
- **FLIGHT_LEG**
  - Owner entity type: FLIGHT
  - Identifying relationship type: LEGS
- **FARE**
  - Owner entity type: FLIGHT
  - Identifying relationship type: FARES
- **SEAT**
  - Owner entity type: LEG_INSTANCE
  - Identifying relationship type: RESERVATION

## Problem 5

**In the following ER diagrams, represent the constraints on the relationship types using the (min, max) notations.**

**(a)**

```mermaid
flowchart LR
    E1 -->|"(1, N)"| R{R}
    R -->|"(0, 1)"| E2
```

**(b)**

```mermaid
flowchart LR
    E1 -->|"(1, M)"| R{R}
    R -->|"(1, N)"| E2
```

**(c)**

```mermaid
flowchart LR
    E1 -->|"(0, n)"| R{R}
    R -->|"(1, 1)"| E2
```

**(d)**

```mermaid
flowchart LR
    E1 -->|"(0, 1)"| R{R}
    R -->|"(1, 1)"| E2
```

**(e)**

```mermaid
flowchart LR
    E1 -->|"(0, M)"| R{R}
    R -->|"(1, N)"| E2
```

**(f)**

```mermaid
flowchart LR
    E1 -->|"(0, 1)"| R{R}
    R -->|"(0, 1)"| E2
```

## Problem 6

**Consider the following ER diagram:**

![Problem 6](./Problem%206.png)

**Determine the (min, max) constraints on all relationship types in this model based on the following assumption:**

- **Each department can have anywhere between one and thirty employees**
- **Each phone belongs to one, and only one, department**
- **Each phone is assigned to at least two and may be assigned to up to four employees**
- **Each employee is assigned at least one but no more than two phones**
- **An employee may work for up to three departments or may not be assigned to any department**
- **A department must have at least one and at most ten phones**

```mermaid
flowchart TB
EMPLOYEE ---|"(0, 3)"| WORKS_IN{WORKS_IN}
WORKS_IN ---|"(1, 30)"| DEPARTMENT
EMPLOYEE ---|"(1, 2)"| HAS_PHONE{HAS_PHONE}
DEPARTMENT ---|"(1, 10)"| CONTAINS{CONTAINS}
HAS_PHONE ---|"(2, 4)"| PHONE
PHONE ---|"(1, 1)"| CONTAINS
```
