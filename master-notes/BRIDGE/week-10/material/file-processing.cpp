#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

void openInputFile(ifstream &input)
{
    do
    {
        input.clear();
        string filename;
        cout << "What is the filename?" << endl;
        cin >> filename;
        input.open(filename);
        if (!input)
            cout << "Failed to open the file!" << endl;
    } while (!input);
}

struct Student
{
    int order;
    double score;
    string name;
};

int main()
{
    // ifstream 和 ofstream 是 istream 和 ostream 的 Sub Class
    // 在做如 week-9/material/operator 的 istream/ostream 的 overloading 時會用到這層關係
    ifstream input;
    openInputFile(input);
    vector<int> listOfInteger;
    int integer;
    while (input >> integer)
    {
        listOfInteger.push_back(integer);
        cout << integer << " ";
    };
    cout << endl;

    int sum = 0;
    for (int item : listOfInteger)
        sum += item;
    cout << "Sum: " << sum << endl;
    input.close();

    openInputFile(input);
    input.seekg(0, input.end); // 移到終點，end 為 end，0 表示無偏移。也可用 ios::end
    int fileSize = input.tellg();
    input.seekg(0, input.beg); // 移到起點，beg 為 begging
    cout << "File size: " << fileSize << endl;
    vector<Student> listOfStudent;
    Student studentInput;
    while (input >> studentInput.order >> studentInput.score)
    {
        input.ignore(10000, '\t');
        getline(input, studentInput.name);
        listOfStudent.push_back(studentInput);
    }
    for (Student student : listOfStudent)
        cout << student.order << "/" << student.score << "/" << student.name << endl;
    input.close();

    ofstream output("output.txt", ios::app); // app 表示 append，可不用傳
    // 或者可用 ofstream output; output.open("output.txt");
    output << "Sum: " + to_string(sum) << "\n";
    output.close();
    return 0;
}