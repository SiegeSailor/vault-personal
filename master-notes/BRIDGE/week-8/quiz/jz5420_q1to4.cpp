/**
 * ============= Question 1:
 *
 * Therom:
 * For every positive integer n, 1 * 2 + 2 * 3 + ... +n(n + 1) = n(n + 1)(n + 2)/3
 *
 * Proof.
 * By induction on n.
 *
 * Base case:
 * P(1),
 * 1(1 + 1) = 1(1 + 1)(1 + 2)/3
 * = 2 = 1(2)(3)/3 = 2
 * P(1) is true.
 *
 * Inductive step:
 * Suppose that for every positive integer n, 1 * 2 + 2 * 3 + ... +n(n + 1) = n(n + 1)(n + 2)/3,
 * we shall prove that 1 * 2 + 2 * 3 + ... +(n + 1)((n + 1) + 1) = (n + 1)((n + 1) + 1)((n + 1) + 2)/3.
 *
 * \sum^{(n + 1)((n + 1) + 1)}_{j = 1} j * (j + 1) = (n + 1)((n + 1) + 1)((n + 1) + 2)/3
 * = \sum^{(n + 1)(n + 2)}_{j = 1} j * (j + 1) = (n + 1)(n + 2)(n + 3)/3
 * = (n(n + 1)(n + 2)/3) + (n + 1)((n + 1) + 1) = (n + 1)(n + 2)(n + 3)/3       By inductive hypothesis
 * = (n^2 + n)(n + 2)/3 + n^2 + 3n + 2 = (n^2 + 3n + 2)(n + 3)/3
 * = (n^3 + 3n^2 + 2n)/3 + 3(n^2 + 3n + 2)/3 = (n^3 + 6n^2 + 11n + 6)/3
 * = (n^3 + 6n^2 + 11n + 6)/3 = (n^3 + 6n^2 + 11n + 6)/3
 *
 * The above are equal. Therefore, for every positive integer n, 1 * 2 + 2 * 3 + ... +n(n + 1) = n(n + 1)(n + 2)/3.
 *
 * ============= Question 2:
 *
 * a) How many bit strings of length 14 contain at most three 1’s?
 *
 * Containing at most three 1’s means containing 0, 1, 2, or 3 1’s,
 *
 * P(14, 3) + P(14, 2) + P(14, 1) + P(14, 0)
 *
 * b) How many bit strings of length 14 contain an equal number of 0’s and 1’s?
 *
 * Containing an equal number of 0’s and 1’s means containing 7 0’s and 7 1’s,
 *
 * P(14, 7)
 *
 * ============= Question 3:
 *
 * a) Find the probability that a randomly generated bit string of length 20 does not
 *    contain a 0 if bits are independent and if the probability that a bit is a 1 is 0.7.
 *
 * The probabilty that a randomly generated bit string of length 20 does not contain a 0
 * means that all the bits are 1. Since all the bits are independent, we get 1 20 times
 * to have 11111111111111111111.
 *
 * (0.7)^20
 *
 *
 * b) Find the probability of at most one success when n independent Bernoulli
 *    trials are carried out with probability of success p.
 *
 * At most one success means 0 success and exact 1 success,
 *
 * P(0) = C(n, 0) * p^0 * (1-p)^(n - 0)
 * P(1) = C(n , 1) * p^1 * (1-p)^(n - 1)
 * The probability of at most one success is P(0) + P(1)
 *
 * ============= Question 4:
 *
 * Analyze its running time of function1 and function2.
 *
 * function1)
 *
 * The function loops from 1 to n (i) and then loops 1 to n (j) in each i. In worst case,
 * we assume that the input n is always true for n % 2 ==0,
 *
 * T(n) = theta(n^2)
 *
 * function2)
 *
 * In the first block, the function loops from 1 to n (i) and then loops 1 to i (j) in each i.
 * k doesn't matter since it is a constant. So far T(n) = theta(n^2).
 * In the second block, the function loops from 1 to n (i) and then loops from i to < 1 (j) in each i.
 *
 * T(n) = theta(2n^2). However, it can be theta(n^2) since we don't really care about constants.
 */