complete BST vs full BST
always adding from left to bottom vs except for leafs all have two children

binary tree example:
http://www.smalltime.com/Dictator

```
50 - 28 -- 12 --- 27
        -- 35
   - 75
```

tree rotation

the sibling of a node should not be bigger than its grandparent

Red black tree - no black or red nodes can connect in order to keep tree balanced. (reduce tree rotation needed)

B Tree

Donald Knuth's book is really good

Array Based Binary Search Tree:

- L child = index of root \* 2 + 1
- R child = index of root \* 2 + 2

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 100 | 50  | 125 | 25  | 75  | 112 | 150 |     |
| R   | L   | R   |     |     |     |     |     |
|     | R   |     | L   | R   |     |     |     |
|     |     | R   |     |     | L   | R   |     |
