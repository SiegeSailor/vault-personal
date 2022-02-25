#include <iostream>
#include <vector>
using namespace std;

void resize(int *&array, int sizeCurrent, int sizeNew);

int main()
{
    int size = 10;
    int *arrayOriginal = new int[size];
    for (int i = 0; i < size; i++)
    {
        arrayOriginal[i] = i * 10;
    }

    int additionValue = 5;
    int sizeNew = size * 2;
    resize(arrayOriginal, size, sizeNew);
    arrayOriginal[size++] = additionValue;

    for (int i = 0; i < sizeNew; i++)
    {
        cout << i << ": " << arrayOriginal[i] << endl;
    }

    vector<int> vectorInteger;
    // vectorInteger.capacity();
    // vectorInteger.resize(vectorInteger.size() - 1);
    // vectorInteger.reserve(50);
    for (int i = 0; i < 100; i++)
    {
        // vectorInteger[i] = i * 100; // 雖然不能直接指定，但可以用 push_back 加到最後
        vectorInteger.push_back(i * 100);
    }

    for (int i : vectorInteger) // 只有數字類的 vector 可以做這個 "forEach"
        cout << i << endl;

    return 0;
}

void resize(int *&array, int sizeCurrent, int sizeNew)
{
    int *arrayTemporary = new int[sizeNew];
    for (int i = 0; i < sizeCurrent; i++)
    {
        arrayTemporary[i] = array[i];
    }

    delete[] array;
    array = arrayTemporary;
}