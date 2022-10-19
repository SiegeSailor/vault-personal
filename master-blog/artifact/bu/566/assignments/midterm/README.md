# Assignment

An assignment for class **MET CS 566** at Boston University, instructed by **Ming Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **October 20, 2022**.

## Question 1

**Ordering by asymptotic growth rates**. Rank the following functions by order of growth. This means finding an arrangement $g_1, g_2, ..., g_9$ of the functions that satisfy $g_1 = \Omega(g_2), g_2 = \Omega(g_3), ..., g_7 = \Omega(g_8)$:

1. $g_1 = \log(\log(n))$
2. $g_2 = 2^{\log(n)}$
3. $g_3 = 2^{\sqrt{2 \times \log(n)}}$
4. $g_4 = n^{8.2} + \log(n)$
5. $g_5 = n^{8.2} + n!$
6. $g_6 = n^{2024} + n^{512} + n \times \log(n)$
7. $g_7 = e^n + e^{ln(n)}$
8. $g_8 = \sqrt{\log(n)}$

Work in stepwise format. Find the fastest growing function first, and then the next fast one.

## Question 2

**Asymptotic notations**. For each pair of the following functions $f(n)$ and $g(n)$, check if:

1. $f(n) = \Theta(g(n))$
2. $f(n) = \mathcal{O}(g(n))$
3. $f(n) = \Omega(g(n))$
4. $f(n) = o(g(n))$
5. $f(n) = \omega(g(n))$

Functions $f(n)$ and $g(n)$ are:

1. $f(n) = 128^{\frac{n}{4}} \ vs.\ g(n) = 512^{\frac{n}{8}}$
2. $f(n) = n^{64} \times 2^n \ vs.\ g(n) = \log(n) \times 2^n+ 4^n + n^{32}$
3. $f(n) = n^{1024} \ vs. \ g(n) = 2^{\log(n) \times \log(n)}$

You do not need to provide a formal proof. Describe your justifications in text form.

## Question 3

**Big O notation**. What is the running time of this code in Big O notation regarding $n$?

```
1. y = 0
2. j = 1
3. while (j * j <= n) do
4.     y = y + 1
5.     j = j + 1
6. end while
```

Line 4 is some constant cost.

## Question 4

**Big O notation**. What is the running time of this code in Big O notation regarding $n$?

```
a = 0
k = n * n
while (k > 1) do
    for j <- 0 to n*n do
        a = a + j
    end for
    k = k / 2
end while
```

## Question 5

**Recursive function**. What is the running time of the following python code in Big O notation regarding $n$?

```Python
def isThis(n):
    print(n)
    if (n == 1):
        return True
    if (n == 0):
        return False
    if ((n % 3) == 0):
        return isThis(n / 3)
    else:
        return False
```

## Question 6

**`HEAP-EXTRACT-MAX` worst case**. Describe when the worst case of `HEAP-EXTRACT-MAX` happens after you removed a max value from a heap. Provide an example.

## Question 7

**Hash function**. Name two properties a good hash function should have. Describe your answer (if possible, with an example).

## Question 8

**Double hashing**. When you use double hashing in a hash table, does the number of potential collisions depend on the sequence of key insertions into the table? In other words, if you change the sequence of insertions, would the number of collisions change? Support your answer using an example.
