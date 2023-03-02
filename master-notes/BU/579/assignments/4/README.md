# Assignments

```sql
USE company;

-- Problem 1 - 1
SELECT pnumber, pname
FROM project, department
WHERE project.dnum = department.dnumber AND department.dname = 'Administration';

-- Problem 1 - 2
SELECT dependent_name
FROM dependent
WHERE essn = '123456789';

-- Problem 1 - 3
SELECT DISTINCT ssn, fname, lname
FROM employee, dependent
WHERE employee.ssn = dependent.essn;

-- Problem 1 - 4
(SELECT ssn, fname, lname
FROM employee, dependent
WHERE dependent.essn = employee.ssn)
UNION
(SELECT ssn, fname, lname FROM
employee, department
WHERE employee.ssn = deparment.mgrssn);

-- Problem 1 - 5
SELECT ssn, fname, lname
FROM employee, works_on, project
WHERE employee.ssn = works_on.essn AND works_on.pno = project.pnumber AND project.name = 'ProjectX';

-- Problem 2 - 1
UPDATE employee SET dno = 1 WHERE fname = 'John' AND lname = 'Smith';

-- Problem 2 - 2
UPDATE employee SET salary = salary * 1.1 WHERE sex = 'F';

-- Problem 2 - 3
UPDATE department
JOIN employee ON employee.fname = 'John' AND employee.lname = 'Smith'
SET department.mgrssn = employee.ssn
WHERE department.dname = 'Research';

-- Problem 3 - 1
DELETE FROM works_on
WHERE pno = 20;

-- Problem 3 - 2
DELETE FROM dept_locations
WHERE dept_locations.dlocation = 'Sugarland';
```
