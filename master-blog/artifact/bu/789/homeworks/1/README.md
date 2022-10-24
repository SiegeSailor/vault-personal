# Programming

A programming practice for class **MET CS 789** at Boston University, instructed by **Geoffrey Pascoe**. Student [Zhang, Jin Yu](siege@bu.edu) created on **September 25, 2022**.

## Related Formulas and Facts

- $n = qm + r$
  - $any number = quotient \times reduction modulo + remainder$
  - An example of hash function
  - $f: n \implies r$
    - ap $Z$ to the set $\{ 0, 1, 2,..., m - 1 \}$
    - $n \ \% \ m$
    - $n \mod m$
  - $n = km$, then $(x \ \% \ n) \ \% \ m = x \ \% \ m$
- $n$ is prime if $n$ is not divisible by any integer from the range $(1, \sqrt(n)$
- $m$ and $n$ are relatively prime if and only if $gcd(m, n) = 1$
- Smallest positiver integer $x_0 \times m + y_0 \times n$ is a divisor of $m$ and $n$
  - $m \ \% \ (x_0 \times m + y_0 \times n) = 0$
  - $n \ \% \ (x_0 \times m + y_0 \times n) = 0$
  - Every divisor $d$ of $m$ and $n$ is a divisor of $x_0 \times m + y_0 \times n$
    - $m \ \% \ d = 0$
    - $n \ \% \ d = 0$
    - $(x_0 \times m + y_0 \times n) \ \% \ d = 0$
    - $x_0 \times m + y_0 \times n$ is the greatest common divisor of $m$ and $n$

## Question 1

Write and test code for the Euclidean Algorithm.

## Question 2

Write and test code that will find a couple of integers, $x_0$ and $y_0$, for given integers $m$ and $n$, such that $x_0 \times m + y_0 \times n$ will yield the smallest positive number. This is, of course, the Extended Euclidean Algorithm.
