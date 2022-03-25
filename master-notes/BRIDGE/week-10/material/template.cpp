#include <iostream>

using namespace std;

template <class T> // template <typename somename>
void swap(T &a, T &b)
{
    T temp = a;
    a = b;
    b = temp;
};

template <class T> // 一定要在宣告上面。跟 TS 的 Generic 很像
class Test
{
private:
    T data;

public:
    T obtainData() const
    {
        return data;
    };
    void setData(T dataInput);
};

template <class T>
void Test<T>::setData(T dataInput)
{
    data = dataInput;
};

int main()
{
    Test<int> test; // 在實體化時給予型別
    test.setData(100);
    cout << test.obtainData() << endl;
    return 0;
}