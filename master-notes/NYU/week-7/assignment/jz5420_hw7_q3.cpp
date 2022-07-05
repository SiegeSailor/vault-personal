#include <iostream>

void oddsKeepEvensFlip(int arr[], int arrSize);
void printResult(int arr[], int arrSize);

int main()
{
    std::cout << "#Test 1" << std::endl;
    const int arrSizeOne = 6;
    int arrOne[arrSizeOne] = {5, 2, 11, 7, 6, 4};
    printResult(arrOne, arrSizeOne);
    std::cout << std::endl;

    std::cout << "#Test 2" << std::endl;
    const int arrSizeTwo = 6;
    int arrTwo[arrSizeTwo] = {1, 2, 3, 4, 5, 6};
    printResult(arrTwo, arrSizeTwo);
    std::cout << std::endl;

    std::cout << "#Test 3" << std::endl;
    const int arrSizeThree = 6;
    int arrThree[arrSizeThree] = {6, 5, 4, 3, 2, 1};
    printResult(arrThree, arrSizeThree);
    std::cout << std::endl;

    return 0;
}

void oddsKeepEvensFlip(int arr[], int arrSize)
{
    int *arrayTemporary = new int[arrSize];
    int indexOfEven = arrSize - 1, indexOfOdd = 0;
    for (int i = 0; i < arrSize; i++)
    {
        int currentValue = arr[i];
        if (currentValue % 2 == 0)
        {
            arrayTemporary[indexOfEven] = currentValue;
            indexOfEven--;
        }
        else
        {
            arrayTemporary[indexOfOdd] = currentValue;
            indexOfOdd++;
        }
    }

    for (int i = 0; i < arrSize; i++)
    {
        arr[i] = arrayTemporary[i];
    }
}

void printResult(int arr[], int arrSize)
{
    std::cout << "Original arr: " << arr << std::endl;
    for (int i = 0; i < arrSize; i++)
    {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;

    oddsKeepEvensFlip(arr, arrSize);
    std::cout << "arr after oddsKeepEvensFlip: " << arr << std::endl;
    for (int i = 0; i < arrSize; i++)
    {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}