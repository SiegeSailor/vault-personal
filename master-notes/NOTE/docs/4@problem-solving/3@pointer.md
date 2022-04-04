---
sidebar_position: 3
---

# Pointer

條列 C++ 中 Pointer 的用法。

## Address

示範 Pointer、Address、Heap Memory 與 Stack Memory 之間的關係。Heap Memory 又稱 Freestore 或 Dynamic Heap Memory：

```cpp
#include <iostream>

using namespace std;

int main()
{
    int example = 10;

    /** 將印出 "10" */
    cout << example << endl;
    /** & 為 Address of Operator，代表取儲存該變數的位址。將印出 "0x7ffe26d176ec" */
    cout << &example << endl;

    /**
     * 此為 Pointer，不可直接給值如 int *pointer = 10。
     * 除非 Dereference、將值 Assign 給指向的變數
     */
    int *pointer;
    /**
     * new 建立的物件存在 Dynamic Heap Memory 中、 而不是 Stack，
     * 是直接回傳位址，因此 Pointer 能直接指向、不需 & 取址。
     * 建立在 Stack 的物件 Function 結束後會自行清除
     */
    pointer = new int;
    /** 將印出 "0x56133dd352e0" */
    cout << new int << endl;
    /** 也可以在 new 建立時直接賦值，仍然取址。將印出 "0x556cf929c2e0" */
    cout << new int(10) << endl;
    /** 或一行結束 */
    pointer = new int(10);
    /** 將印出 "0x556cf929c300" */
    cout << pointer << endl;

    /** 特別注意的是， */

    return 0;
};
```

:::info 提醒
若是直接將 Array 印出來，會發現無論是存在 Heap 或是 Stack 內，都是印出位址，這是因為如 Array 在位址 0x16d0fb500、有 Size 10，則操作該 Array 則代表操作 0x16d0fb500 與其後的 10 個位址：

```cpp
int main()
{
    int *pointer = new int[2];
    /** 以下將印出 "0x60000048c000"   */
    cout << pointer << endl;
    int array[2] = {2, 3};
    /** 以下將印出 "0x16d0fb500" */
    cout << array << endl;
    return 0;
};
```

:::

### In Function

在 Function Parameter 和 Return Value 中，Pointer `*` 和 `&` 也會有影響：

```cpp
#include <iostream>

using namespace std;

/**
 * 表示將 Integer Pointer 作為 Return Type，
 * 直接建立在 Heap 中
 */
int *createPointer(int input)
{
    return new int(input);
};

/**
 * 表示將 Integer Pointer 作為 Parameter Type，
 * Call by Value，所做改變不影響傳入的 Pointer
 */
void acceptPointer(int *pointer)
{
    /** 將印出 "0x55bfccc22eb0" */
    cout << pointer << endl;
    pointer = new int(50);
};

/**
 * 表示將 Integer Pointer 作為 Parameter Type，
 * Call by Reference，所做改變將影響傳入的 Pointer
 */
void acceptPointerReference(int *&pointer)
{
    /** 將印出 "0x55bfccc22eb0" */
    cout << pointer << endl;
    pointer = new int(50);
};

/**
 * 接受 Pointer of Pointer，
 * 意即指向 Pointer 的 Pointer
 */
void acceptPointerOfPointer(int **pointer)
{
    /** 將印出 "0x55bfccc22eb0" */
    cout << pointer << endl;
};

/**
 * 表示將 Parameter 轉為位址傳入，
 * 在 Function 內可以直接使用，
 * Call by Reference，所做改變會影響該傳入的變數
 */
void acceptStatic(int &input)
{
    cout << input << endl;
    input = 100;
};

int main()
{
    int input = 10;
    /** 由於 Return Type 是 Integer Pointer，因此以 int * 宣告 */
    int *pointer = createPointer(input);

    /** 輸入的 Parameter 為 Pointer */
    acceptPointer(pointer);
    /** 仍然印出 "10"，因為雖然傳入 Pointer，但 acceptPointer 的 Parameter 為 Call by Value */
    cout << *pointer << endl;
    /** 輸入的 Parameter 為 Pointer Reference */
    acceptPointerReference(pointer);
    /** 將印出 "50"，因為 acceptPointerReference 的 Parameter 為 Call by Reference */
    cout << *pointer << endl;

    /** Pointer of Pointer，即指向位址為一 Pointer 的 Pointer */
    int **pointerOfPointer = &pointer;
    acceptPointerOfPointer(pointerOfPointer);

    /** 對輸入的 Static Parameter 取址 */
    acceptStatic(input);
    /** 將印出 "100"，因 input 在 acceptStatic 內被變更 */
    cout << input << endl;

    return 0;
};
```

## Deference

示範如何藉由 Deference Operator `*` 直接改動背後的變數：

```cpp
#include <iostream>

using namespace std;

int main()
{
    int example = 10;
    int *pointer;

    /** 將印出 "10" */
    cout << example << endl;
    /**
     * * 為 Deference Operator，能夠將位址換為值。
     * 在這先將 pointer 指向 example，
     * 再使用 * 來 Deference 後賦值，直接改動指向的 example
     */
    pointer = &example;
    /** 將印出 "0x7ffe26d176ec"，為 example 之位址 */
    cout << pointer << endl;
    *pointer = 20;
    /** 將印出 "20" */
    cout << example << endl;
    /** 仍然印出 "0x7ffe26d176ec" */
    cout << pointer << endl;
    /** 將印出 "20"。注意 Deference Operator */
    cout << *pointer << endl;

    return 0;
};
```

:::info 提醒
將 Deference 用在 Array 上，則會印出該 Array 的第一個值：

```cpp
#include <iostream>

using namespace std;

int main()
{
    int array[2] = {2, 3};
    /** 以下將印出 "2" */
    cout << *array << endl;

    int *pointer = new int[2];
    pointer[0] = 10;
    /** 以下將印出 "10" */
    cout << *pointer << endl;
    return 0;
};
```

:::

:::caution 注意
注意 NULL 與 nullptr 無法使用 Deference，因背後無值。若嘗試，會報錯 _Segmentation Fault_，此錯誤始在企圖存取無法被定址的記憶體區段：

```cpp
#include <iostream>

using namespace std;

int main()
{
    int *pointer = NULL;
    /** 以下將報錯 "Segmentation Fault" */
    cout << *pointer << endl;
    pointer = nullptr;
    /** 以下將報錯 "Segmentation Fault" */
    cout << *pointer << endl;
    return 0;
};
```

:::

## Delete

示範 `delete` 在不同類型的值上的情況：

```cpp
#include <iostream>

using namespace std;

int main()
{
    int *pointer;
    /** 將印出 "0x10027bd0c"，儘管尚未指向、仍然有自己的位址 */
    cout << pointer << endl;
    /**
     * 將報錯 "Pointer being freed was not allocated"，
     * 因為此 Pointer 並未指向任何記憶體
     */
    delete pointer;
    /**
     * 將報錯 "double free detected in tcache"，
     * 因為 delete 是告訴 OS 拿回在 Heap 內的記憶體，
     * 不可重複拿回
     */
    delete pointer;
    /** 為避免以上情況，在取回後應立刻賦予 NULL 或 nullptr，
     * 因為此二種值可以被重複 delete
     */
    pointer = nullptr;
    delete pointer;
    delete pointer;

    pointer = new int[10];
    /** 注意 delete 的對象若是 Array，則必須加上 "[]" */
    delete[] pointer;

    return 0;
};
```
