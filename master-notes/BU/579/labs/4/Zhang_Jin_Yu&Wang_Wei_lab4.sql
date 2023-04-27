USE lab;

-- Problem 1
DELIMITER //
DROP PROCEDURE IF EXISTS emp_info //
CREATE PROCEDURE emp_info(IN employee_identifier CHAR(16))
BEGIN
	DECLARE employee_name VARCHAR(64);
	DECLARE employee_salary INT;
    
	SELECT
		employee.name,
		employee.salary
	INTO
		employee_name,
		employee_salary
	FROM
		employee
	WHERE
		employee.identifier = employee_identifier;
    
    SELECT
		employee_name as 'Name',
		CONCAT('$', employee_salary) as 'Salary';
END //
DELIMITER ;

CALL emp_info('EMPL001');

-- Problem 2
DELIMITER  //
DROP PROCEDURE IF EXISTS emp_orders //
CREATE PROCEDURE emp_orders(IN employee_identifier CHAR(16))
BEGIN
	SELECT
        employee.name AS 'Employee Name',
        customer.name AS 'Customer Name',
        sum(shoe_order.amount) AS 'Order Amount' 
	FROM
        employee,
        customer,
        shoe_order
    WHERE
        employee.identifier = shoe_order.employee_identifier AND
        customer.identifier = shoe_order.customer_identifier AND
        employee.identifier = employee_identifier
    GROUP BY
        employee.name,
        customer.name;
END //
DELIMITER ;

CALL emp_orders('EMPL001');

-- Problem 3
DELIMITER //
DROP FUNCTION IF EXISTS get_phone //
CREATE FUNCTION get_phone(customer_name VARCHAR(64), branch_number TINYINT)
    RETURNS CHAR(10)
    DETERMINISTIC
BEGIN
    DECLARE branch_phone CHAR(10);

    SELECT
        branch.phone
    INTO
        branch_phone
    FROM
        branch,
        customer
    WHERE
        branch.customer_identifier = customer.identifier AND
        customer.name = customer_name AND
        branch.number = branch_number;

	RETURN branch_phone;
END //
DELIMITER ;

SELECT
	get_phone('Jane Smith', 2) AS 'Branch Phone';
    
-- Problem 4
DELIMITER //
DROP TRIGGER IF EXISTS max_types //
CREATE TRIGGER max_types
	BEFORE INSERT ON order_item
    FOR EACH ROW
BEGIN
	DECLARE count TINYINT;
    
    SELECT
        COUNT(DISTINCT order_item.shoe_type_identifier)
    INTO
        count
    FROM
        order_item
    WHERE
        order_item.suborder_shoe_order_number = new.suborder_shoe_order_number AND
        order_item.suborder_number = new.suborder_number;

    IF
        count >= 2 AND
        new.shoe_type_identifier NOT IN (
            SELECT
                DISTINCT order_item.shoe_type_identifier
            FROM
                order_item
            WHERE
                order_item.suborder_shoe_order_number = new.suborder_shoe_order_number AND
                order_item.suborder_number = new.suborder_number
        )
    THEN
        SIGNAL SQLSTATE '45000'
    SET
        message_text = 'A suborder can only include at most two shoe types.';
    END IF;
END //
DELIMITER ;

-- Problem 4 Manual Review
SELECT
	order_item.suborder_shoe_order_number AS 'Suborder Shoe Order Number',
	order_item.suborder_number AS 'Suborder Number',
    shoe_type.identifier
FROM
    order_item,
    shoe_type
WHERE
	order_item.shoe_type_identifier = shoe_type.identifier
GROUP BY
	order_item.suborder_shoe_order_number,
    order_item.suborder_number,
    shoe_type.identifier
ORDER BY
	order_item.suborder_shoe_order_number,
    order_item.suborder_number;

INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
	VALUES (8, 1, 'SHOE001', 100); -- No violation
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
	VALUES (8, 1, 'SHOE003', 100); -- Violates the trigger

-- Problem 5
DELIMITER //
DROP TRIGGER IF EXISTS date_inconsistency //
CREATE TRIGGER date_inconsistency
	BEFORE INSERT ON suborder
    FOR EACH ROW
BEGIN
    DECLARE shoe_order_date DATE;

    SELECT
        DISTINCT shoe_order.date
    INTO
        shoe_order_date
    FROM
        suborder,
        shoe_order
    WHERE
        suborder.shoe_order_number = shoe_order.number AND
        shoe_order.number = new.shoe_order_number;

    IF
        new.request_ship_date < shoe_order_date
    THEN
        SIGNAL SQLSTATE '45000'
    SET
        message_text = 'The required shipping date of a suborder can not be earlier than its parent order date.';
    END IF;
END //
DELIMITER ;

INSERT INTO suborder (shoe_order_number, number, request_ship_date)
	VALUES (1, 4, '2024-03-24'); -- No violation
INSERT INTO suborder (shoe_order_number, number, request_ship_date)
	VALUES (1, 5, '2014-03-24'); -- Violates the trigger

