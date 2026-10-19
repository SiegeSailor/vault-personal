-- CS579 Lab 3
-- Zhang Jin Yu & Wang Wei

USE lab;

INSERT INTO customer (identifier, name, address, phone)
VALUES ('CUST001', 'John Doe', '123 Main St, Cityville', '5551234567');
INSERT INTO customer (identifier, name, address, phone)
VALUES ('CUST002', 'Jane Smith', '456 Oak St, Townville', '5559876543');
INSERT INTO customer (identifier, name, address, phone)
VALUES ('CUST003', 'Alice Johnson', '789 Elm St, Village', '5553217890');

INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL001', 'Robert Brown', '123 Maple St, Cityville', 'assistant', 30000);
INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL002', 'Emily White', '456 Pine St, Townville', 'associate', 40000);
INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL003', 'Michael Green', '789 Willow St, Village', 'manager', 50000);
INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL004', 'Jin Yu Zhang', '123 Maple St, Cityville', 'assistant', 100000);
INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL005', 'Wang Wei', '456 Pine St, Townville', 'associate', 100000);
INSERT INTO employee (identifier, name, address, class, salary)
VALUES ('EMPL006', 'Ding Nan Shi', '789 Willow St, Village', 'manager', 50000);

INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2023-03-22', 1, 'CUST001', 'EMPL002');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2022-02-21', 2, 'CUST001', 'EMPL002');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2022-02-21', 3, 'CUST002', 'EMPL003');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2021-05-02', 4, 'CUST002', 'EMPL003');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2020-11-15', 5, 'CUST003', 'EMPL001');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2020-12-01', 6, 'CUST003', 'EMPL001');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2019-02-14', 7, 'CUST001', 'EMPL001');
INSERT INTO shoe_order (date, amount, customer_identifier, employee_identifier)
VALUES ('2019-04-08', 8, 'CUST001', 'EMPL001');

INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST001', 1, '111 First St, Cityville', '5551112222');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST001', 2, '222 Second St, Cityville', '5552223333');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST001', 3, '333 Third St, Cityville', '5553334444');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST002', 1, '11 First St, Cityville', '6661112222');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST002', 2, '22 Second St, Cityville', '6662223333');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST002', 3, '33 Third St, Cityville', '6663334444');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST003', 1, '1 First St, Cityville', '7771112222');
INSERT INTO branch (customer_identifier, number, address, phone)
VALUES ('CUST003', 2, '2 Second St, Cityville', '7772223333');


INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (1, 1, '2023-03-25', '2023-03-24', 'CUST001', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (1, 2, '2023-03-26', '2023-03-25', 'CUST001', 2);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (2, 1, '2023-03-25', '2023-03-24', 'CUST001', 3);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (2, 2, '2023-03-26', '2023-03-25', 'CUST001', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (3, 1, '2023-03-25', '2023-03-24', 'CUST002', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (3, 2, '2023-03-26', '2023-03-25', 'CUST002', 2);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (4, 1, '2023-03-25', '2023-03-24', 'CUST002', 3);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (4, 2, '2023-03-26', '2023-03-25', 'CUST002', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (5, 1, '2023-03-25', '2023-03-24', 'CUST003', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (5, 2, '2023-03-26', '2023-03-25', 'CUST003', 2);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (6, 1, '2023-03-25', '2023-03-24', 'CUST003', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (6, 2, '2023-03-26', '2023-03-25', 'CUST003', 2);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (7, 1, '2023-03-25', '2023-03-24', 'CUST001', 3);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (7, 2, '2023-03-26', '2023-03-25', 'CUST001', 2);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (8, 1, '2023-03-25', '2023-03-24', 'CUST001', 1);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (8, 2, '2023-03-26', '2023-03-25', 'CUST001', 3);
INSERT INTO suborder (shoe_order_number, number, request_ship_date, actual_ship_date, branch_customer_identifier, branch_number)
VALUES (8, 3, '2023-03-25', '2023-03-27', 'CUST001', 1);

INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE001', 'mens_dress', 10.5, 250);
INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE002', 'mens_casual', 11, 300);
INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE003', 'mens_running', 12.5, 100);
INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE004', 'womens_dress', 6, 10);
INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE005', 'womens_casual', 7.5, 100);
INSERT INTO shoe_type (identifier, name, size, price)
VALUES ('SHOE006', 'womens_running', 8.5, 1000);

INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE001', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE001', 12);

INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE002', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE002', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE003', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE003', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE004', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE004', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE005', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE005', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 1, 'SHOE006', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (1, 2, 'SHOE006', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 1, 'SHOE001', 25);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 2, 'SHOE001', 22);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 1, 'SHOE002', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 2, 'SHOE002', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 1, 'SHOE003', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 2, 'SHOE003', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 1, 'SHOE004', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (2, 2, 'SHOE004', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (3, 1, 'SHOE003', 26);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (3, 2, 'SHOE003', 12);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (4, 1, 'SHOE004', 22);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (4, 2, 'SHOE004', 32);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (5, 1, 'SHOE005', 24);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (5, 2, 'SHOE005', 52);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (6, 1, 'SHOE006', 26);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (6, 2, 'SHOE006', 72);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (7, 1, 'SHOE001', 28);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (7, 2, 'SHOE001', 92);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (8, 1, 'SHOE002', 20);
INSERT INTO order_item (suborder_shoe_order_number, suborder_number, shoe_type_identifier, quantity)
VALUES (8, 2, 'SHOE002', 12);