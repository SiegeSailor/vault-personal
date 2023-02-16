# Assignment

An assignment for class **MET CS 579** at Boston University, instructed by **Jae Lee**. Student [Jin Yu Zhang](siege@bu.edu) submitted on **February 16, 2023**.

## Problem 1

Describe the difference between a key, a super key, a candidate key, and a primary key:

### Solution

- **Key**: A key is a minimal set of one or more attributes (columns) that can uniquely identify a record or tuple within a table. There may be more than one key for a table, but one of the keys is designated as the primary key. A key is a super key (minimal) but not vice versa
- **Super Key**: A super key is a set of one or more attributes that, taken collectively, allow us to identify uniquely a tuple or record within a table. A super key may contain extraneous attributes, which are not necessary for uniquely identifying a tuple or record
- **Candidate Key**: A candidate key is a minimal super key (key) that uniquely identifies each tuple or record within a table. In other words, it is a super key with no extraneous attributes. A table may have more than one candidate key
- **Primary Key**: A primary key is one of the candidate keys that is chosen to uniquely identify tuples or records within a table. It is a special type of candidate key that is used to enforce data integrity and ensure that there are no duplicate records in the table. The primary key must be unique and cannot contain null values. A table can have only one primary key, but it may have multiple candidate keys

Overall, a table can only have a primary key, and a primary key is chosen from the candidate keys from the table. Candidate keys are keys, and a key is a minimal super key.

## Problem 2

When a delete operation violates a referential integrity constraint, there are following four options the system can take. Describe each option using an example:

### Solution

- **Restrict**: This option prevents the deletion of a parent record if there are any child records associated with it. The DBMS will throw an error and prevent the deletion from occurring. For example, suppose we have a database with two tables, "Customers" and "Orders". The "Orders" table has a foreign key constraint that references the "CustomerID" column in the "Customers" table. If we try to delete a customer record that has associated orders, the DBMS will not allow the deletion and will throw an error
- **Cascade**: To attempt to propagate the deletion by deleting tuples that reference the tuple that is being deleted. This option automatically deletes all the child records associated with the parent record being deleted. For example, using the same database as above, if we delete a customer record that has associated orders, the DBMS will automatically delete all the associated orders
- **Set Null**: This option sets the foreign key value in the child records to null if the parent record is deleted. For example, suppose we have a database with two tables, "Departments" and "Employees". The "Employees" table has a foreign key constraint that references the "DepartmentID" column in the "Departments" table. If we delete a department record that has associated employees, the DBMS will set the "DepartmentID" value in the employee records to null
- **Set Default**: This option sets the foreign key value in the child records to a default value if the parent record is deleted. For example, suppose we have a database with two tables, "Authors" and "Books". The "Books" table has a foreign key constraint that references the "AuthorID" column in the "Authors" table. If we delete an author record that has associated books, the DBMS will set the "AuthorID" value in the book records to a default value (e.g., 0 or -1)

## Problem 3

Suppose each of the following operations is applied directly to the database of Figure 5.6. Indicate **all** integrity constraints violated by each operation, if any:

### Solution

- **(a)**: Referential Integrity. The referenced tuple with department number `3` doesn't exist
- **(b)**: Key Integrity. There is a tuple already exist with department number `4`
- **(c)**: None
- **(d)**: Referential Integrity. The referenced tuple with department number `6` doesn't exist
- **(e)**: Key Integrity. There is a tuple already exist with ESSN `453453453` and project number `2`
- **(f)**: None
- **(g)**: Referential Integrity. The referenced tuple with SSN `234234234` doesn't exist
- **(h)**: Referential Integrity. `888665555` has been referenced by other tuples

## Problem 4

Consider the following relational database schema:

```
STUDENT(Student_ID, First_name, Last_name, Department, Degree_program)
DEPARTMENT(Dept_name, Location)
COURSE(Course_no, Course_name, Department)
TEXTBOOK(Course_no, Semester, ISBN)
BOOK(ISBN, Title, Publisher, Author)
```

Identify all foreign keys in the above schema and write each foreign key using the following
format:

```
<foreign key name> in <relation name> references <attribute name> in <relation name>
```

An example (in the company database) is:

```
Dno in EMPLOYEE references Dnumber in DEPARTMENT
```

### Solution

```
Department in STUDENT references Dept_name in DEPARTMENT
Department in COURSE references Dept_name in DEPARTMENT
Course_no in TEXTBOOK references Course_no in COURSE
ISBN in TEXTBOOK references ISBN in BOOK
```

## Problem 5

Consider the following relational database schema:

```
CAR(VIN, Year, Make, Model, Price)
OPTION(VIN, Option_package, Price)
SALE(Salesperson_ID, VIN, Sale_price, Date)
SALESPERSON(Emp_ID, Name, Phone)
```

Identify all foreign keys in the above schema and write each foreign key using the following
format:

```
<foreign key name> in <relation name> references <attribute name> in <relation name>
```

An example (in the company database) is:

```
Dno in EMPLOYEE references Dnumber in DEPARTMENT
```

### Solution

```
VIN in OPTION references VIN in CAR
VIN in SALE references VIN in CAR
Salesperson_ID in SALE references Emp_ID in SALESPERSON
```
