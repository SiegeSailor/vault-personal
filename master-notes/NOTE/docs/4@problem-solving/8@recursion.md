---
sidebar_position: 8
---

# Recursion

Stack 有 LIFO (Last-In/First-Out) 原則，就像是一疊疊的紙，當有新的出現時、疊在最上面，而當要讀時，從最上面的開始。Recursive Function 以此為基礎，在定義 Base Case (Stopping Case) 後以不同的參數呼叫自己、並在呼叫前後做計算或 I/O，最終在輸入參數達到 Stopping Case 條件後停止呼叫自己。

最重要的特性是，程式執行時遇到 Function 會執行到該 Function Return 為止、再接著執行，也就是離 Stopping Case 越近的 Function 會更早完成。

:::info 提醒
有別疊紙譬喻，事實上 Stack 是使用記憶體的區塊 (Activation Frame、Call Stack)、Reference 到指定 Function。
:::

以一個倒數 Recursive Function `countdown` 為例：

```cpp title="countdown.cpp"
#include <iostream>
using namespace std;

void countdown(int input);

int main()
{
    countdown(5);
    return 0;
}

void countdown(int input)
{
    // highlight-next-line
    // 以 If-Else 劃分出的 Base Case，在這同樣作為 Stopping Case 用
    if (input == 1)
    {
        cout << input << endl;
    }
    else
    {
        // highlight-next-line
        // 符合條件，也就是 P(5)、P(4)...P(2) 時，再次呼叫自己
        countdown(input - 1);
        // highlight-start
        /**
         * 輸入 5 時，到此應該要印出 5，但由於呼叫自己在前，
         * 除非被呼叫的 Function 結束，否則不會執行下面的程式
         * 因此 5 會發生在 4 之前。以此類推，1 會是第一個
         */
        // highlight-end
        cout << input << endl;
    }
}
```

```cli
% clang++ countdown.cpp -o countdown.out
% ./countdown.out
1
2
3
4
5
```

:::info 提醒
Base Case 是在 Recursive Function 中終止執行的情境，也可以想成是能保證執行的第一個值。Base Case 必定是 Stopping Case，但 Stopping Case 不一定是 Base Case，如 Binary Search。
:::

### Recursion Principle

1. 不做 Infinite Recursion (Stack Overflow)
2. 確保每個 Base Case 回傳正確值
3. 藉由保證每個 Call 的正確，確保最終結果 (Strong Induction)

:::info 提醒
Stack Overflow 錯誤源自堆疊超出 Stack Size Limit。而最常見的原因就是 Infinite Recursion。理論上 Infinite Recursion 不會自動終止，而若發生則原因幾乎都是不正常的 (如 Stack Overflow)。
:::

## Iteration

由於需要 Trace Stack，Recursion 通常比 Iteration 更慢、消耗更多資源。通常而言，Recursion 可以辦到的都可以由 Loop (Iteration) 辦到，甚至有些語言不允許 Recursion。會使用 Recursion 的原因，是因為在某些場合會更簡單跟乾淨。

這邊以 Binary Search 做範例。Recursive Function 為：

```cpp
#include <iostream>
using namespace std;

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue);

int main()
{
    const int size = 10;
    // 搜尋對象必須為 Ordered Ascending
    int listOfNumber[size] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int value, indexOfValue;
    bool isFound;
    cout << "Enter a value to search:" << endl;
    cin >> value;

    search(listOfNumber, 0, size - 1, value, isFound, indexOfValue);
    if (isFound)
        cout << value << " is at position " << indexOfValue << " of the list." << endl;
    else
        cout << value << " is not in the list." << endl;

    return 0;
}

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue)
{
    // highlight-next-line
    // Stopping Case
    if (indexOfStart > indexOfEnd)
    {
        isFound = false;
    }
    else
    {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        // highlight-next-line
        // Base Case
        if (value == listOfNumber[indexOfMiddle])
        {
            isFound = true;
            indexOfValue = indexOfMiddle;
        }
        // highlight-next-line
        // 根據兩種不同情況，以不同參數再次呼叫自己
        else if (value < listOfNumber[indexOfMiddle])
            search(listOfNumber, indexOfStart,
                   indexOfMiddle - 1, value,
                   isFound, indexOfValue);
        else if (value > listOfNumber[indexOfMiddle])
            search(listOfNumber, indexOfMiddle + 1,
                   indexOfEnd, value,
                   isFound, indexOfValue);
    }
}
```

Iteration 為：

```cpp
void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue)
{
    isFound = false;
    while ((indexOfStart <= indexOfEnd) && !isFound)
    {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        if (value == listOfNumber[indexOfMiddle])
        {
            isFound = true;
            indexOfValue = indexOfMiddle;
        }
        else if (value < listOfNumber[indexOfMiddle])
            indexOfEnd--;
        else if (value > listOfNumber[indexOfMiddle])
            indexOfStart++;
    }
}
```
