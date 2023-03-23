-- CS579 Lab 3
-- Zhang Jin Yu & Wang Wei

USE lab;

-- a)
SELECT number, date, amount
FROM shoe_order
WHERE customer_identifier = 'CUST001';

-- b)
SELECT identifier, name, COUNT(shoe_order.number) AS count
FROM employee
JOIN shoe_order ON identifier = employee_identifier
GROUP BY identifier, name
HAVING count >= 2;

-- c)
SELECT number, address, phone
FROM branch
WHERE customer_identifier = 'CUST002';

-- d)
SELECT shoe_order_number, suborder.number, request_ship_date, actual_ship_date, customer.name, branch_number, employee.name
FROM suborder, customer, shoe_order, employee
WHERE suborder.actual_ship_date > suborder.request_ship_date
AND branch_customer_identifier = customer.identifier
AND suborder.shoe_order_number = shoe_order.number
AND shoe_order.employee_identifier = employee.identifier;

-- e)
SELECT name, branch.number, branch.phone, branch.address
FROM customer, branch
WHERE branch.customer_identifier = customer.identifier
AND EXISTS (
SELECT *
FROM suborder
WHERE branch_number = branch.number
AND suborder.actual_ship_date >= '2023-03-01'
AND suborder.actual_ship_date <= '2023-03-31');
-- or
SELECT name, branch.number, branch.phone, branch.address
FROM customer, branch
WHERE branch.customer_identifier = customer.identifier
AND EXISTS (
SELECT *
FROM suborder
WHERE branch_number = branch.number
AND YEAR(suborder.actual_ship_date) = '2023'
AND MONTH(suborder.actual_ship_date) = '03');