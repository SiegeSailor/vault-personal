#include <iostream>
using namespace std;

int main()
{
    cout << "=== pointer_1" << endl;

    int x = 100;
    int *pointer_1;
    pointer_1 = &x; // 加& 是因為這樣才能取該 static variable 的 address。pointer 相反，沒加 * 代表拿 address
    cout << "x: " << x << endl;
    cout << pointer_1 << ": " << *pointer_1 << endl;
    *pointer_1 = 20; // pointer 加 * 代表 dereference 取值，所以可以直接 assign 20 給 pointer_1 背後的 address (x)
    cout << "x: " << x << endl;
    cout << pointer_1 << ": " << *pointer_1 << endl;
    x = 30;
    cout << "x: " << x << endl;
    cout << pointer_1 << ": " << *pointer_1 << endl;

    cout << "=== pointer_2_1, pointer_2_2" << endl;

    int *pointer_2_1 = nullptr;
    int *pointer_2_2 = NULL;
    cout << pointer_2_1 << endl;
    cout << pointer_2_2 << endl;
    // cout << pointer_2_1 << ": " << *pointer_2_1 << endl; 因為是 null、取不到值 (* 無法 dereference)
    // cout << pointer_2_2 << ": " << *pointer_2_2 << endl;

    cout << "=== pointer_3" << endl;

    int *pointer_3 = new int; // 存在 dynamic "heap" memory 而不是 "stack"。所有其他都是存在 stack、function 結束即清除
    cout << pointer_3 << ": " << *pointer_3 << endl;
    *pointer_3 = 1000;
    cout << pointer_3 << ": " << *pointer_3 << endl;

    cout << "=== deleting" << endl;

    // delete pointer_1; // 不可 delete 因為 pointer_1 指向的是在 stack 內的值
    delete pointer_2_1;
    delete pointer_2_1;
    delete pointer_2_2; // 可以一直 delete 因為是 nullptr 或 NULL
    delete pointer_2_2;
    delete pointer_3;
    // delete pointer_3; // 不是 nullptr 或 NULL 的話 delete 兩次會報錯，因為 delete 是告訴 OS 拿回被 new 在 heap 內的記憶體
    pointer_3 = nullptr; // new 出來的 pointer 在 delete 後應馬上給 nullptr 或 NULL
    delete pointer_3;

    cout << "=== array" << endl;
    int y;
    cin >> y;
    int *pointer_4 = new int[y];
    pointer_4[0] = 1;
    cout << pointer_4 << ": " << *pointer_4 << endl;
    delete[] pointer_4; // delete array 時一定要加[]、以告訴編譯器要刪除的是 array，其中之一的原因是我們不能單獨刪除 array 中的值
    int static_array[2] = {1, 2};
    cout << static_array << ": " << *static_array << endl;

    return 0;
}