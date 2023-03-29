---
title: Techniques
description: Tips and techniques from doing LeetCode.
slug: techniques
authors: maintainer
tags: [interview]
---

## Print

Always print out the inputs, the data structure may be very different from the statement.

# Hash Map

See [299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows/description/). With hash map, we need to pay attention for `if` in a `for-loop` since all stream fall into the first `if` will execute first, so the value changing for the hash map should take it account.

## Binary Search Tree

Remember the order. For example, `left` must be smaller than `node`, and `right` is bigger than `node`, so the current one will always be smaller than the next one in in-order traversal.

## Stack & Queue

In the loops, we can use indices to slice, or variables to keep value from previous.

## Pointers

It is not necessary to update the value of pointers in every iteration. See [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan&id=level-1), the **One Pass** solution doesn't update `profit_max` whenever the smaller `price_min` is found:

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        price_min : int = prices[0]
        profit_max: int = 0
        for price in prices:
            # highlight-start
            if price < price_min:
                price_min = price
            elif (price - price_min) > profit_max:
                profit_max = price - price_min
            # highlight-end

        return profit_max
```
