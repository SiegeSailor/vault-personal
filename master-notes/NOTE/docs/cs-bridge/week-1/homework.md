---
marp: true
theme: gaia
paginate: true
header: 'NYU CS Bridge - Winter 2022'
footer: 'NET ID: jz5420 | Name: Zhang, Jin Yu'
style: |
  section {
  }
  ol, ul {
    margin: 0;
  }
  p, li, table {
      font-size: 16px;
  }
  h4 {
      font-size: 18px;
  }
  footer {
      font-size: 12px;
  }
sidebar_position: 3
title: Homework
---

<!-- header: 'Question 1' -->

**A. Convert the following numbers to their decimal representation. Show your work.**

```bash
1. 10011011₂
- 1*2⁷ + 0*2⁶ + 0*2⁵ + 1*2⁴ + 1*2³ + 0*2² + 1*2¹ + 1*2⁰
- 128 + 0 + 0 + 16 + 8 + 0 + 2 + 1
- 155 # Answer
```

```bash
2. 456₇
- 4*7² + 5*7¹ + 6*7⁰
- 196 + 35 + 6
- 237 # Answer
```

---

```bash
3. 38A₁₆
- 3*16² + 8*16¹ + A*16⁰ # A is 10
- 768 + 128 + 10
- 906 # Answer
```

```bash
4. 2214₅
- 2*5³ + 2*5² + 1*5¹ + 4*5⁰
- 250 + 50 + 5 + 4
- 309 # Answer
```

---

**B. Convert the following numbers to their binary representation:**

```bash
1. 69₁₀
- 2⁶ is 64
- 1*2⁷ + 1*2⁶ + 0*2⁵ + 0*2⁴ + 0*2³ + 1*2² + 0*2¹ + 1*2⁰
- 69
- 0100 0101 # Answer
```

```bash
2. 485₁₀
- 2⁸ is 256
- 0*2⁹ + 1*2⁸ + 1*2⁷ + 1*2⁶ + 1*2⁵ + 0*2⁴ + 0*2³ + 1*2² + 0*2¹ + 1*2⁰
- 485
- 01 1110 0101 # Answer
```

---

```bash
3. 6D1A₁₆
- Convert to Base Ten first
- A*16⁰ + 1*16¹ + D*16² + 6*16³
- 10 + 16 + 3328 + 24576
- 6D1A₁₆ =  27930₁₀
- 2¹⁴ = 16384
- 1*2¹⁴ + 1*2¹³ + 0*2¹² + 1*2¹¹ + 1*2¹⁰ + 0*2⁹ + 1*2⁸ + 0*2⁷ \
  + 0*2⁶ + 0*2⁵ + 1*2⁴ + 1*2³ + 0*2² + 1*2¹ + 0*2⁰
- 27930
- 110110100011010 # Answer
```

---

**C. Convert the following numbers to their hexadecimal representation:**

```bash
1. 1101011₂
- Use the following table:
| --------- | ------------ |
| 5         | 0101         |
| 6         | 0110         |
| 7         | 0111         |
| 8         | 1000         |
| 9         | 1001         |
| A         | 1010         |
| B         | 1011         |
| C         | 1100         |
| --------- | ------------ |
- 0110 1011
- 6B # Answer
```

---

```bash
2. 895₁₀
- Convert to Base Two first
- 2⁹ is 512
- 0*2¹⁰ + 1*2⁹ + 1*2⁸ + 0*2⁷ + 1*2⁶ + 1*2⁵ + 1*2⁴ + 1*2³ + 1*2² \
  + 1*2¹ + 1*2⁰
- 895₁₀ = 0011 0111 1111₂
- Use the following table:
| --------- | ------------ |
| 3         | 0011         |
| 4         | 0100         |
| 5         | 0101         |
| 6         | 0110         |
| 7         | 0111         |
| ...       | ...          |
| F         | 1111         |
| --------- | ------------ |
- 37F # Answer
```

---

<!-- header: 'Question 2' -->

**Solve the following, do all calculation in the given base. Show your work.**

```bash
1. 7566₈ + 4515₈

 7566₈
+4515₈
------
    3
   0
  3
 4
1
-----
14303 # Answer
```

---

```bash
2. 10110011₂ + 1101₂

 1011 0011₂
+0000 1101₂
-----------
         0
        0
       0
      0
    0
   0
  1
 1
-----------
 1100 0000 # Answer
```

---

```bash
3. 7A66₁₆ + 45C5₁₆

 7A66₁₆
+45C5₁₆
-------
    B
   2
  0
 C
------
 C02B # Answer
```

---

```bash
4. 3022₅ - 2433₅

 3022₅
+2433₅
------
    0
   1
  0
 1
1
-----
11010 # Answer
```

---

<!-- header: 'Question 3' -->

**A. Convert the following numbers to their 8-bits two complement representation. Show your work.**

```bash
1. 124₁₀
- Convert 124₁₀ to Base Two first
- 2⁶ is 64
- 0*2⁷ + 1*2⁶ + 1*2⁵ + 1*2⁴ + 1*2³ + 1*2² + 0*2¹ + 0*2⁰
- 124₁₀ = 0111 1100₂ # Answer
```

```bash
2. -124₁₀
- First, 124₁₀ = 0111 1100₂

 0111 1100₂
+1000 0100₂  # Answer: 1000 0100₂
-----------
10000 0000₂ 2⁸
```

---

```bash
3. 109₁₀
- Convert 109₁₀ to Base Two first
- 2⁶ is 64
- 0*2⁷ + 1*2⁶ + 1*2⁵ + 0*2⁴ + 1*2³ + 1*2² + 0*2¹ + 1*2⁰
- 109₁₀ = 0110 1101₂ # Answer
```

```bash
4. -79₁₀
- Convert 79₁₀ to Base Two first
- 2⁶ is 64
- 0*2⁷ + 1*2⁶ + 0*2⁵ + 0*2⁴ + 1*2³ + 1*2² + 1*2¹ + 1*2⁰
- 79₁₀ = 0100 1111₂

 0100 1111₂
+1011 0001₂ # Answer: 1011 0001₂
-----------
10000 0000₂ 2⁸
```

---

**B. Convert the following numbers (represented as 8-bit two’s complement) to their decimal representation. Show your work.**

```bash
1. 00011110 8 bit 2’s comp
- This is a positive number since it starts with 0
- Convert 00011110₂ to Base Ten
- 0*2⁷ + 0*2⁶ + 0*2⁵ + 1*2⁴ + 1*2³ + 1*2² + 1*2¹ + 0*2⁰
- 30 # Answer
```

```bash
2. 11100110 8 bit 2’s comp
- This is a negative number since it starts with 1
- Use two’s complement method to decode

 1110 0110₂
+0001 1010₂ = 26₁₀ # Answer: -26₁₀
-----------
10000 0000₂ 2⁸
```

---

```bash
3. 00101101 8 bit 2’s comp
- This is a positive number since it starts with 0
- Convert 00101101₂ to Base Ten
- 0*2⁷ + 0*2⁶ + 1*2⁵ + 0*2⁴ + 1*2³ + 1*2² + 0*2¹ + 1*2⁰
- 45 # Answer
```

```bash
4. 10011110 8 bit 2’s comp
- This is a negative number since it starts with 1
- Use two’s complement method to decode

 1001 1110₂
+0110 0010₂ = 98₁₀ # Answer: -98₁₀
-----------
10000 0000₂ 2⁸
```

---

<!-- header: 'Question 4' -->

**Solve the following questions from the Discrete Math zyBook:**

**1. Exercise 1.2.4, sections b, c**: Write a truth table for each expression.

`b: ¬(p ∨ q)`

| p   | q   | ¬(p ∨ q) |
| --- | --- | -------- |
| T   | T   | F        |
| T   | F   | F        |
| F   | T   | F        |
| F   | F   | T        |

`c: r ∨ (p ∧ ¬q)`

| p   | q   | r   | r ∨ (p ∧ ¬q) |
| --- | --- | --- | ------------ |
| T   | T   | T   | T            |
| T   | T   | F   | F            |
| T   | F   | T   | T            |
| T   | F   | F   | T            |
| F   | T   | T   | T            |
| F   | T   | F   | F            |
| F   | F   | T   | T            |
| F   | F   | F   | F            |

---

**2. Exercise 1.3.4, sections b, d**: Give a truth table for each expression.

`b: (p → q) → (q → p)`

| p   | q   | p → q | q → p | (p → q) → (q → p) |
| --- | --- | ----- | ----- | ----------------- |
| T   | T   | T     | T     | T                 |
| T   | F   | F     | T     | T                 |
| F   | T   | T     | F     | F                 |
| F   | F   | T     | T     | T                 |

`d: (p ↔ q) ⊕ (p ↔ ¬q)`

| p   | q   | p ↔ q | p ↔ ¬q | (p ↔ q) ⊕ (p ↔ ¬q) |
| --- | --- | ----- | ------ | ------------------ |
| T   | T   | T     | F      | T                  |
| T   | F   | F     | T      | T                  |
| F   | T   | F     | T      | T                  |
| F   | F   | T     | F      | T                  |

---

<!-- header: 'Question 5' -->

**Solve the following questions from the Discrete Math zyBook:**

**1. Exercise 1.2.7, sections b, c**: Consider the following pieces of identification a person might have in order to apply for a credit card:

- B: Applicant presents a birth certificate.
- D: Applicant presents a driver's license.
- M: Applicant presents a marriage license.

Write a logical expression for the requirements under the following conditions:

`b: The applicant must present at least two of the following forms of identification: birth certificate, driver's license, marriage license.`

_(B ∧ D) ∨ (B ∧ M) ∨ (M ∧ D)_

`c: Applicant must present either a birth certificate or both a driver's license and a marriage license.`

_B ∨ (D ∧ M)_

**2. Exercise 1.3.7, sections b – e**: Define the following propositions:

- s: a person is a senior
- y: a person is at least 17 years of age
- p: a person is allowed to park in the school parking lot

Express each of the following English sentences with a logical expression:

`b: A person can park in the school parking lot if they are a senior or at least seventeen years of age.`

_(s ∨ y) → p_

`c: Being 17 years of age is a necessary condition for being able to park in the school parking lot.`

_y → p_

---

`d: A person can park in the school parking lot if and only if the person is a senior and at least 17 years of age.`

_p ↔ (s ∧ y)_

`e: Being able to park in the school parking lot implies that the person is either a senior or at least 17 years old.`

_p → (s ∨ y)_

**3. Exercise 1.3.9, sections c, d**: Use the definitions of the variables below to translate each English statement into an equivalent logical expression.

- y: the applicant is at least eighteen years old
- p: the applicant has parental permission
- c: the applicant can enroll in the course

`c: The applicant can enroll in the course only if the applicant has parental permission.`:

_c → p_

`d: Having parental permission is a necessary condition for enrolling in the course.`

_p → c_

---

<!-- header: 'Question 6' -->

**Solve the following questions from the Discrete Math zyBook:**

**1. Exercise 1.3.6, sections b - d**: Give an English sentence in the form "If...then...." that is equivalent to each sentence.

`b: Maintaining a B average is necessary for Joe to be eligible for the honors program.`

_If Joe maintains a B average, then he will be eligible for the honors program._

`c: Rajiv can go on the roller coaster only if he is at least four feet tall.`

_If Rajiv can go on the roller coaster, then he is at least four feet tall._

`d: Rajiv can go on the roller coaster if he is at least four feet tall.`

_If Rajiv is at least four feet tall, then he can go on the roller coaster._

---

**2. Exercise 1.3.10, sections c - f**: The variable p is true, q is false, and the truth value for variable r is unknown. Indicate whether the truth value of each logical expression is true, false, or unknown.

`c: (p ∨ r) ↔ (q ∧ r)`

- (T ∨ r) ↔ (F ∧ r)
- r ↔ F
- _false_

`d: (p ∧ r) ↔ (q ∧ r)`

- (T ∧ r) ↔ (F ∧ r)
- r ↔ r
- _true_

`e: p → (r ∨ q)`

- T → (r ∨ F)
- T → r
- _unknown_

`f: (p ∧ q) → r`

- (T ∧ F) → r
- F → r
- _true_

---

<!-- header: 'Question 7' -->

**Solve Exercise 1.4.5, sections b – d, from the Discrete Math zyBook:** Define the following propositions:

- j: Sally got the job.
- l: Sally was late for her interview
- r: Sally updated her resume.

Express each pair of sentences using logical expressions. Then prove whether the two expressions are logically equivalent.

`b: If Sally did not get the job, then she was late for her interview or did not update her resume. If Sally updated her resume and was not late for her interview, then she got the job.`
