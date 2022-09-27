Cisco assessment:

1. 將一個 MxN 的 matrix 右轉 90 度，如：
   [1,2],[3,4] = [4,1],[2,3]

2. (ab){2}(c(de){3}f) = ababcdededef

3. 給 20 與 5，找出 20 以下兩個數字加起來等於 5 的值的總數，如 05+14 共 2 個

```java
public class Main {
    public boolean binarySearch(int[] array, int target, int indexStart, int indexEnd) {
        if (indexStart > indexEnd)
            return false;

        int indexMiddle = (indexStart + indexEnd) / 2;

        if (array[indexMiddle] == target)
            return true;
        else if (array[indexMiddle] < target)
            return this.binarySearch(array, target, indexMiddle + 1, indexEnd);
        else
            return this.binarySearch(array, target, indexStart, indexMiddle - 1);
    }

    public int determine(int[] array, int target) {
        int total = 0;
        for (int index = 0; index < array.length / 2; index++) {
            if (array[index] > target)
                continue;
            else if (this.binarySearch(array, target - array[index], 0, array.length - 1))
                total++;
        }

        return total / 2;
    }

    public static void main(String[] args) {
        int upper = 20;
        int[] array = new int[upper];
        for (int i = 0; i < array.length; i++)
            array[i] = i + 1;

        int target = 5;

        System.out.println("The number of pair of digits below " + upper + " sum up to " + target + " is " + new Main().determine(array, target));
    }
}
```
