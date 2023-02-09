# Lab 1

An assignment for class **MET CS579** Database Management at Boston University due on **February 9, 2023**, instructed by **Jae Lee**. Students [Jin Yu Zhang](siege@bu.edu) and [Wei Wang](wwwemma820@gmail.com) submitted on **February 8, 2023**.

## Assumptions

Description for things not included in the requirements:

- Customer is not an entity since it doesn't have any attribute, and we assume that each customer has only one headquarter by the definition of a headquarter. Therefore, customer and `HEADQUARTER` are equivalent here
- Each customer has one or more branch stores. We assume that there is no limit for the number of `Branch_Store` for a `HEADQUARTER`
- According to the requirements, we should keep the information about the retail store who issued the order for an order. However, since there is already a relationship between `ORDER` and `HEADQUARTER`, we will not put this as an attribute in `ORDER`
- According to the requirements, we should keep the information about the employee(s) of the Boston Shoes who handle the order for an order. However, since there is already a relationship between `ORDER` and `EMPLOYEE`, we will not put this as an attribute in `ORDER`. Also the requirement says an employee handles at most four order, we assume an employee is allowed to handle no orders
- There are not limits for how much suborders a `BRANCH_STORE` can be shipped to, and how many suborders can a `SHOES_TYPE` hold. There are hence form with `N` (many) constraint in this assignment
- We assume an `EMPLOYEE` come with only one classification

## Questions

Known issues:

- Is sequence number a derived value? If it is, how do we define the partial key for `SUBORDER` and `BRANCH_STORE`?
- 5-2, 5-4, and 6-2. Should we store them as attributes or relationship types are enough?

<style>
    caption {
        font-weight: bold;
        font-size: large;
        padding: 12px;
    }

    code {
      font-weight: bold;
      background: rgba(175,184,193,0.2);
    }

    table {
        width: 100%;
    }

    tr, th, td {
        border: 1px solid;
    }

    td {
        vertical-align: top;
    }

    th[rowspan="2"] {
        padding-top: 38px;
    }

    ol {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
</style>

<div style="page-break-after: always;"></div>

<table>
  <caption>
    Entity Types
  </caption>
  <colgroup span="3"></colgroup>
  <tr>
    <th rowspan="2">Name</th>
    <th rowspan="2">Description</th>
    <th colspan="4" style="text-align: center">Attribute</th>
  </tr>
  <tr>
    <th>Name</th>
    <th>Description</th>
    <th>Multivalued</th>
    <th>Derived</th>
  </tr>
  <tr>
    <td>EMPLOYEE</td>
    <td>Employees works for Boston Shoes.</td>
    <td>
      <ol>
        <li>Id {K}</li>
        <li>Name</li>
        <li style="padding-left:12px;">Fname</li>
        <li style="padding-left:12px;">Minit</li>
        <li style="padding-left:12px;">Lname</li>
        <li>Salary</li>
        <li>Address</li>
        <li>Classification</li>
      </ol>
    </td>
    <td></td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li> </li>
        <li> </li>
        <li> </li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li> </li>
        <li> </li>
        <li> </li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>SHOES_TYPE</td>
    <td></td>
    <td>
      <ol>
        <li>Id {K}</li>
        <li>Name</li>
        <li>Size</li>
        <li>Price</li>
      </ol>
    </td>
    <td></td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>ORDER</td>
    <td></td>
    <td>
      <ol>
        <li>Number {K}</li>
        <li>Date</li>
        <li>Amount</li>
      </ol>
    </td>
    <td></td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>SUBORDER</td>
    <td></td>
    <td>
      <ol>
        <li>Number {WK}</li>
        <li>Required_shipping_date</li>
        <li>Actual_shipping_date</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>Number is a sequence number starting with 1 and is only unique among those that belong to a particular order.</li>
        <li></li>
        <li></li>
      </ol>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>HEADQUARTER</td>
    <td>The customers of Boston Shoes such as Walmart, Target, and Kohls.</td>
    <td>
      <ol>
        <li>Id {K}</li>
        <li>Name</li>
        <li>Address</li>
        <li>Phone_number</li>
      </ol>
    </td>
    <td></td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>BRANCH_STORE</td>
    <td></td>
    <td>
      <ol>
        <li>Number {WK}</li>
        <li>Address</li>
        <li>Phone_number</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>Number is a sequence number starting with 1 and is only unique among those that belong to the same retail store.</li>
        <li></li>
        <li></li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>N</li>
        <li>N</li>
        <li>N</li>
      </ol>
    </td>
  </tr>
</table>

<div style="page-break-after: always;"></div>

<table>
  <caption>
    Relationship Types
  </caption>
  <colgroup span="3"></colgroup>
  <tr>
    <th rowspan="2">Name</th>
    <th rowspan="2">Description</th>
    <th colspan="2" style="text-align: center">Participating Entity Types</th>
    <th rowspan="2">Attribute</th>
  </tr>
  <tr>
    <th>Name</th>
    <th>Structural Constraint</th>
  </tr>
  <tr>
    <td>OWNS</td>
    <td>Each customer (headquarter) has one or more branch stores.</td>
    <td>
      <ol>
        <li>HEADQUARTER</li>
        <li>BRANCH_STORE</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(1, N)</li>
        <li>(1, 1)</li>
      </ol>
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>ISSUES</td>
    <td>An order is issued by a retail store headquarter.</td>
    <td>
      <ol>
        <li>HEADQUARTER</li>
        <li>ORDER</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(1, N)</li>
        <li>(1, 1)</li>
      </ol>
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>HANDLES</td>
    <td>Boston Shoes employees handle the order.</td>
    <td>
      <ol>
        <li>EMPLOYEE</li>
        <li>ORDER</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(0, 4)</li>
        <li>(1, 1)</li>
      </ol>
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>CONSISTS_OF</td>
    <td>An order consists of one or more suborders.</td>
    <td>
      <ol>
        <li>SUBORDER</li>
        <li>ORDER</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(1, 1)</li>
        <li>(1, N)</li>
      </ol>
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>SHIPS_TO</td>
    <td>A suborder is shipped to exactly one branch store.</td>
    <td>
      <ol>
        <li>SUBORDER</li>
        <li>BRANCH_STORE</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(1, 1)</li>
        <li>(0, N)</li>
      </ol>
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>INCLUDES</td>
    <td>A suborder includes one or more shoe types.</td>
    <td>
      <ol>
        <li>SUBORDER</li>
        <li>SHOES_TYPE</li>
      </ol>
    </td>
    <td>
      <ol>
        <li>(1, N)</li>
        <li>(0, N)</li>
      </ol>
    </td>
    <td>
      Quantity
    </td>
  </tr>
</table>

<div style="page-break-after: always;"></div>

![ER Diagram](./MET%20CS579.drawio.png)
