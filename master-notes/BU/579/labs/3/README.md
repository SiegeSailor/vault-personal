- [Lab 3](#lab-3)
  - [Assumptions](#assumptions)
  - [Screenshots](#screenshots)

# Lab 3

An assignment for class **MET CS579** Database Management at Boston University due on **March 23, 2023**, instructed by **Jae Lee**. Students [Jin Yu Zhang](siege@bu.edu) and [Wei Wang](wwwemma820@gmail.com) submitted on **March 23, 2023**.

## Assumptions

Some assumptions regarding to SQL statements:

- `class` from `employee` table is said to be in `{ assistant, associate, manager }` when it comes to the inserting section. However, there is no general limitation so we set it to `VARCHAR(16)` to allow further expanding
- `name` from `shoe_type` table is said to be in `{ mens_dress, mens_casual, mens_running, womens_dress, womens_casual, womens_running }` when it comes to the inserting section. However, there is no general limitation so we set it to `VARCHAR(64)` to allow further expanding
- Sine the database is for Boston Shoe Company, `phone`, `address`, and `name` fields are for U.S, hence are said to be `CHAR(10), VARCHAR(128), VARCHAR(64)`
- All `address` and `name` are said to be `NOT NULL` due to the natural of business. You claim an order/customer only when you know who they are and where they live
- `customer_identifier` and `employee_identifier` in `shoe_order` table is `NOT NULL` since an order always need party A and party B. This relationship was also specified in Lab 1 (`(1,1)`)
- `number` in `branch` and `suborder` tables is said to be unique among certain customers/shoe orders in Lab 1, however, it is not specified in the relational tables so we simply ignore it. To apply, we would need to use syntax such as `CREATE TRIGGER` or `CHECK`
- There are some constraints we wanted to add but haven't been covered in class (beyond slides 7-21), so we ignore it in this lab:
  - General cross-table constraints for `phone`, `address`, and `name`
  - Specify the constraint that `request_ship_date` and `actual_ship_date` from `suborder` table can not be earlier than `date` from their referenced `shoe_order` tuple
  - The total number of `quantity` from `order_time` for a `shoe_order` should be equal to `amount` for the `shoe_order` tuple

## Screenshots

Create tables:

![Create Table](./Create%20Tables.png)

Insert Tuples:

![Insert Tuples](./Insert%20Tuples.png)

Query results are in [Zhang_Jin_Yu&Wang_Wei_query_results.pdf](./Zhang_Jin_Yu%26Wang_Wei_query_results.pdf).
