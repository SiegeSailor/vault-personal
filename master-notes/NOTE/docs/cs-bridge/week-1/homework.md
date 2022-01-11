---
marp: true
theme: gaia
paginate: true
header: 'NYU CS Bridge - Winter 2022'
footer: 'NET ID: jz5420 | Name: Zhang, Jin Yu'
style: |
  section {
  }
  p, li {
      font-size: 16px;
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
- 124₁₀ = 0111 1100₂
 0111 1100₂ 124
+____ ____₂ -124
-----------
10000 0000₂ 2⁸
```

---

```bash
3. 109₁₀
```

```bash
4. -79
```
