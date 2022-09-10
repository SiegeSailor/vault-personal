---
tags: ["java"]
---

The idea came from [LeetCode 200. Number of Islands](https://leetcode.com/problems/number-of-islands/). When solving graph questions, we need an queue to keep track of a location for BFS either a stack for DFS. How do we put the locations into our data structure?

## Pure Math

```java
public int encode(int indexOfRow, int indexOfColumn, int lengthOfColumn) {
    return indexOfRow * lengthOfColumn + indexOfColumn;
}

public int decode(int code, int lengthOfColumn, String method) {
    switch(method) {
        case "ROW":
            return code / lengthOfColumn;
        case "COLUMN":
            return code % lengthOfColumn;
    }
}
```

For example, we pick a location at `(1, 4)` on a `5 x 5` grid and encode it:

```cmd
1 * 5 + 4 = 9
```

Then we decode code `9` to retrieve its original coordinate:

```cmd
9 / 5 = 1
```

```cmd
9 % 5 = 4
```

:::info
This is fast and we can use `Integer` for any other data structure we may use. However, it is quite hard to read when we need to go top or right. For instance, we need to decode the code from `9` to get `(1, 4)` in order to go to `(1, 5)`.
:::

## Array

```java
this.queue.add(new int[] {x, y});
```

`queue` in the code can be any other list-like data structure. With `int[]`, we can simply enter the coordinate into a structure, but it is not comparable. When it comes to structures such as `HashSet`, or any other scenario. For example, the following will not be functional:

```java
HashSet<int []> set = new HashSet<int []>();

set.add(new int[] {1, 4});
if (set.contains(new int[] {1, 4}))
    System.out.println("This line will never be reached.");
else
    System.out.println("Arrays are not comparable.");
```

:::info
Fast and even more semantic than using pure math. The only drawback is that arrays are not comparable. It is useful when there is no need to compare with two coordinates.
:::

## Custom Class

```java
public class Coordinate {
    private int x;

    private int y;

    public Coordinate(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
    }

    @Override
	public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.y;
        return result;
	}

    @Override
    public boolean equals(Object input) {
        if (this == input)
            return true;
        if (input == null)
            return false;
        if (getClass() != input.getClass())
            return false;
        Coordinate that = (Coordinate) input;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
```

:::info
Comparable and semantic. It is just too time-consuming when you have to write a whole class for every graph question.
:::
