# Assignment

An assignment for class **MET CS 566** at Boston University, instructed by **Ming Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **October 2, 2022**.

## Correction

Score: `19/20`

### Question 3

You need to un-pad back to a $3 \times 3$ matrix after calculation.

### Question 5

The question for 5.3 is actually $T(n) = 2T(\frac{n}{4}) + n^2$, hence, a=2, b=4, d=2. We should use master theorem case 2, which is $\theta(n^2)$.

## Question 1

Use Strassen’s algorithm to compute the following matrix product. Show your work in different computation steps:

$$
C =
\begin{pmatrix}
1 & 3 \\
7 & 5 \\
\end{pmatrix}
\begin{pmatrix}
6 & 8 \\
4 & 2 \\
\end{pmatrix}
=
\begin{pmatrix}
18 & 14 \\
62 & 66 \\
\end{pmatrix}
$$

Let `C` be the multiplication product, `A` be the first matrix and `B` be the second matrix:

$$
C = A \times B
$$

We can then name each value:

$$
\begin{pmatrix}
C_{11} & C_{12} \\
C_{21} & C_{22} \\
\end{pmatrix}
=
\begin{pmatrix}
A_{11} & A_{12} \\
A_{21} & A_{22} \\
\end{pmatrix}
\begin{pmatrix}
B_{11} & B_{12} \\
B_{21} & B_{22} \\
\end{pmatrix}
$$

Let's define the following 7 matrices:

$$
M_1 = (A_{11} + A_{22}) \times (B_{11} + B_{22}) \\
M_2 = (A_{21} + A_{22}) \times B_{11} \\
M_3 = A_{11} \times (B_{12} - B_{22}) \\
M_4 = A_{22} \times (B_{21} - B_{11}) \\
M_5 = (A_{11} + A_{12}) \times B_{22} \\
M_6 = (A_{21} - A_{11}) \times (B_{11} + B_{12}) \\
M_7 = (A_{12} - A_{22}) \times (B_{21} + B_{22})
$$

Put `A` and `B` in the matrices:

$$
M_1 = (1 + 5) \times (6 + 2) = 48 \\
M_2 = (7 + 5) \times 6 = 72 \\
M_3 = 1 \times (8 - 2) = 6 \\
M_4 = 5 \times (4 - 6) = -10 \\
M_5 = (1 + 3) \times 2 = 8 \\
M_6 = (7 - 1) \times (6 + 8) = 84 \\
M_7 = (3 - 5) \times (4 + 2) = -12
$$

<div style="page-break-after: always;"></div>

We can use the matrices in the following formula to calculate each value in the multiplication product's matrix:

$$
C_{11} = M_1 + M_4 - M_5 + M_7 = 48 + (-10) - 8 + (-12) = 18 \\
C_{12} = M_3 + M_5 = 6 + 8 = 14 \\
C_{21} = M_2 + M_4 = 72 + (-10) = 62 \\
C_{22} = M_1 + M_3 - M_2 + M_6 = 48 + 6 - 72 + 84 = 66 \\
$$

We can verify the answer with Matrix Multiplication:

$$
(1 \times 6) + (3 \times 4) = 18 \\
(1 \times 8) + (3 \times 2) = 14 \\
(7 \times 6) + (5 \times 4) = 62 \\
(7 \times 8) + (5 \times 2) = 66 \\
$$

<div style="page-break-after: always;"></div>

## Question 2

Write pseudocode for Strassen’s Algorithm (assume the input is the power of 2). You can use similar pseudocode syntax similar to the CLRS book or our lecture slides.

```
Strassen's Algorithm
Parameter: A, B (Matrix, 2 level array)

l = A.row.length
C = l x l matrix
if l == 1
    C[1][1] = A[1][1] * B[1][1]
else
    ATL = A[1..l / 2][1..l / 2]
    ATR = A[1..l / 2][l / 2 + 1..l]
    ABL = A[l / 2 + 1..l][1..l / 2]
    ABR = A[l / 2 + 1..l][l / 2 + 1..l]
    BTL = B[1..l / 2][1..l / 2]
    BTR = B[1..l / 2][l / 2 + 1..l]
    BBL = B[l / 2 + 1..l][1..l / 2]
    BBR = B[l / 2 + 1..l][l / 2 + 1..l]

    # The followings are sum of matrices. S1, ..., S0 are matrices
    S1 = BTR - BBR
    S2 = ATL + ATR
    S3 = ABL + ABR
    S4 = BBL - BTL
    S5 = ATL + ABR
    S6 = BTL + BBR
    S7 = ATR - ABR
    S8 = BBL + BBR
    S9 = ABL - ATL
    S0 = BTL + BTR

    # Recursively divide into smaller matrices
    M1 = Strassen's Algorithm(S5, S6)
    M2 = Strassen's Algorithm(S3, BTL)
    M3 = Strassen's Algorithm(ATL, S1)
    M4 = Strassen's Algorithm(ABR, S4)
    M5 = Strassen's Algorithm(S2,  BBR)
    M6 = Strassen's Algorithm(S9,  S0)
    M7 = Strassen's Algorithm(S7, S8)

    C[1..l / 2][1..l / 2] = M1 + M4 - M5 + M7
    C[1..l / 2][l / 2 + 1..l] = M3 + M5
    C[l / 2 + 1..l][1..l / 2] = M2 + M4
    C[l / 2 + 1..l][l / 2 + 1..l] = M1 + M3 - M2 - M6
    return C
```

<div style="page-break-after: always;"></div>

## Question 3

Is it possible to use Strassen’s Algorithm to compute the following matrix multiplication?

$$
C =
\begin{pmatrix}
1 & 3 & 2\\
7 & 5 & 2\\
4 & 2 & 1\\
\end{pmatrix}
\begin{pmatrix}
6 & 8 & 6\\
4 & 2 & 1\\
5 & 3 & 1\\
\end{pmatrix}
$$

First of all, we know strassen's algorithm requires matrices with $2^n \times 2^n$:

$$
\begin{pmatrix}
C_{11} & C_{12} & C_{13} & C_{14} \\
C_{21} & C_{22} & C_{23} & C_{24} \\
C_{31} & C_{32} & C_{33} & C_{34} \\
C_{41} & C_{42} & C_{43} & C_{44} \\
\end{pmatrix}
=
\begin{pmatrix}
A_{11} & A_{12} & A_{13} & A_{14} \\
A_{21} & A_{22} & A_{23} & A_{24} \\
A_{31} & A_{32} & A_{33} & A_{34} \\
A_{41} & A_{42} & A_{43} & A_{44} \\
\end{pmatrix}
\begin{pmatrix}
B_{11} & B_{12} & B_{13} & B_{14} \\
B_{21} & B_{22} & B_{23} & B_{24} \\
B_{31} & B_{32} & B_{33} & B_{34} \\
B_{41} & B_{42} & B_{43} & B_{44} \\
\end{pmatrix}
$$

So we pad the matrices with zeroes:

$$
\begin{pmatrix}
C_{11} & C_{12} & C_{13} & C_{14} \\
C_{21} & C_{22} & C_{23} & C_{24} \\
C_{31} & C_{32} & C_{33} & C_{34} \\
C_{41} & C_{42} & C_{43} & C_{44} \\
\end{pmatrix}
=
\begin{pmatrix}
1 & 3 & 2 & 0\\
7 & 5 & 2 & 0\\
4 & 2 & 1 & 0\\
0 & 0 & 0 & 0
\end{pmatrix}
\begin{pmatrix}
6 & 8 & 6 & 0\\
4 & 2 & 1 & 0\\
5 & 3 & 1 & 0\\
0 & 0 & 0 & 0
\end{pmatrix}
$$

After padding with zeroes, the inputs are eligible for Strassen's algorithm and we can calculate it with steps mentioned in Question 1 and Question 2.

Since we can pad zeroes to a $3 \times 3$ matrix, we should be able to use Strassen's algorithm for **any** matrix multiplication as long as we can pad zeroes the closet $2^n$ dimension. For example, a $7 \times 7$ matrix can be a $8 \times 8$ matrix by padding a column and a row with zeroes.

Padding zeroes **doesn't result in any increasing running time** because the length of padding columns/rows will not be larger than $2 \times length \ of \ rows$.

<div style="page-break-after: always;"></div>

## Question 4

Use the Substitution Method for solving the following recurrences:

### $T(n) = T(n - 1) + n$ is $\mathcal{O}(n^2)$

We need to prove $T(n) \leq \mathcal{O}(n^2)$ for a constant $c > 0$. We assume this bond should hold for all positive $m < n$, in particular for $m = n - 1$, $T(n - 1) \leq c \times (n - 1)^2$.

$$
\left\{
\begin{matrix}
    T(n) = T(n - 1) + n \\
    T(n - 1) \leq c \times (n - 1)^2
\end{matrix}
\right.
$$

$$
T(n) \leq (c \times (n - 1)^2 - 1) + n \\
T(n) \leq (c \times (n^2 - (2 \times n)) + 1 - 1) + n \\
T(n) \leq (c \times (n^2 - (2 \times n))) + n \\
T(n) \leq (c \times n^2) - (c \times 2 \times n) + n
$$

$\leq c \times n^2$ holds as long as $c \geq 1$.

### $T(n) = T(\lceil \frac{n}{2} \rceil) + 1$ is $\mathcal{O}(\log^n)$

We need to prove $T(n) \leq \mathcal{O}(\log^n) - d$ for constants $c > 0$ and $d \geq 0$. We assume this bond should hold for all positive $m < n$, in particular for $m = \lceil \frac{n}{2} \rceil$, $T(\lceil \frac{n}{2} \rceil) \leq (c \times \log^{\lceil \frac{n}{2} \rceil}) - d$.

$$
\left\{
\begin{matrix}
    T(n) = T(\lceil \frac{n}{2} \rceil) + 1 \\
    T(\lceil \frac{n}{2} \rceil) \leq (c \times \log^{\lceil \frac{n}{2} \rceil}) - d
\end{matrix}
\right.
$$

$$
T(n) \leq ((c \times \log^{\lceil \frac{n}{2} \rceil}) - d) + 1 \\
T(n) \leq c \times \log^{\lceil \frac{n}{2} \rceil}
$$

$\leq (c \times \log^{\lceil \frac{n}{2} \rceil}) - d$ holds as long as $c \geq 1$ and $d \geq 0$.

<div style="page-break-after: always;"></div>

## Question 5

Use the Master Method for solving the following recurrences:

$$
T(n) = a \times T \times (\frac{n}{b}) + \mathcal{O}(n^d) \\
T(n) =
\left\{
\begin{matrix}
    \mathcal{O}(n^d \times \log^n) \ if \ a = b^d \\
    \mathcal{O}(n^d) \ if \ a < b^d \\
    \mathcal{O}(n^{\log_b^a}) \ if \ a > b^d
\end{matrix}
\right.
$$

### $T(n) = 2T(\frac{n}{4}) + 1$

$$
a = 2, b = 4, d = 0. \ a > b^d, \mathcal{O}(n^{\log_4^2})
$$

### $T(n) = 2T(\frac{n}{4}) + \sqrt{n}$

$$
a = 2, b = 4, d = \frac{1}{2}. \ a = b^d, \mathcal{O}(\sqrt{n} \times \log^n)
$$

### $T(n) = 2T(\frac{n}{4}) + n$

$$
a = 2, b = 4, d = 1. \ a < b^d, \mathcal{O}(n)
$$
