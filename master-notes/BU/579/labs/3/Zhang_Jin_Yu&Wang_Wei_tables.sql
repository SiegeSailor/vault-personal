-- CS579 Lab 3
-- Zhang Jin Yu & Wang Wei

CREATE DATABASE lab;

USE lab;

CREATE TABLE customer (
    identifier                 CHAR(16)                 PRIMARY KEY,
    name                       VARCHAR(64)              NOT NULL,
    address                    VARCHAR(128)             NOT NULL,
    phone                      CHAR(10)
);

CREATE TABLE employee (
    identifier                 CHAR(16)                 PRIMARY KEY,
    name                       VARCHAR(64)              NOT NULL,
    address                    VARCHAR(128)             NOT NULL,
    class                      VARCHAR(16),
    salary                     INT
);

CREATE TABLE shoe_order (
    number                     BIGINT                   NOT NULL AUTO_INCREMENT,
    date                       DATE                     NOT NULL,
    amount                     INT                      DEFAULT 1,
    customer_identifier        CHAR(16)                 NOT NULL,
    employee_identifier        CHAR(16)                 NOT NULL,
    FOREIGN KEY (customer_identifier) REFERENCES customer (identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (employee_identifier) REFERENCES employee (identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (number)
);

CREATE TABLE branch (
    customer_identifier        CHAR(16),
    number                     TINYINT,
    address                    VARCHAR(128)             NOT NULL,
    phone                      CHAR(10),
    FOREIGN KEY (customer_identifier) REFERENCES customer (identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (customer_identifier, number)
);

CREATE TABLE suborder (
    shoe_order_number          BIGINT,
    number                     BIGINT,
    request_ship_date          DATE,
    actual_ship_date           DATE,
    branch_customer_identifier CHAR(16),
    branch_number              TINYINT,
    FOREIGN KEY (shoe_order_number) REFERENCES shoe_order (number) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (branch_customer_identifier, branch_number) REFERENCES branch (customer_identifier, number) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (shoe_order_number, number)
);

CREATE TABLE shoe_type (
    identifier                 CHAR(16)                 PRIMARY KEY,
    name                       VARCHAR(64)              NOT NULL,
    size                       DECIMAL(3,1),
    price                      INT
);

CREATE TABLE order_item (
    suborder_shoe_order_number BIGINT,
    suborder_number            BIGINT,
    shoe_type_identifier       CHAR(16),
    quantity                   INT,
    FOREIGN KEY (suborder_shoe_order_number, suborder_number) REFERENCES suborder (shoe_order_number, number) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (shoe_type_identifier) REFERENCES shoe_type (identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (suborder_shoe_order_number, suborder_number, shoe_type_identifier)
);