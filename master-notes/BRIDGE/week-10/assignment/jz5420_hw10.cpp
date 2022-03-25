#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

const string MESSAGE_OPEN_FILE_FAILED = "WARNING: Failed to open the file!",
             MESSAGE_OPEN_FILE_SUCCESSFULLY = "SUCCESS: Opened the file successfully!",
             MESSAGE_PREFIX = "    - ";
const string TITLE_PAYROLL_TOP = "********* Payroll Information *********",
             TITLE_PAYROLL_BOTTOM = "************* End payroll *************";

void fileRead(ifstream &input, string message)
{
    do
    {
        input.clear();
        string filename;
        cout << endl
             << message << endl;
        cin >> filename;
        input.open(filename);
        if (!input)
            cout << MESSAGE_OPEN_FILE_FAILED << endl;
        else
            cout << MESSAGE_OPEN_FILE_SUCCESSFULLY << endl;
    } while (!input);
};

struct EmployeeInformation
{
    int identity;
    double payrateHour;
    string name;
};

struct EmployeeWorkHour
{
    int identity;
    int hour;
};

class Employee
{
private:
    int identity;
    double payrateHour;
    string name;
    int hour = 0;

public:
    Employee(int identity) : identity(identity), payrateHour(0.0), name(""){};
    Employee(EmployeeInformation information)
        : identity(information.identity),
          payrateHour(information.payrateHour),
          name(information.name)
    {
        cout << MESSAGE_PREFIX
             << "identity: " << information.identity << ", "
             << "name: " << information.name << ", "
             << "payrateHour: " << information.payrateHour << endl;
    };

    bool operator==(const Employee &that)
    {
        return this->identity == that.identity;
    };
    bool operator<(const Employee &that)
    {
        return this->inuqrePay() < that.inuqrePay();
    };
    bool operator>(const Employee &that)
    {
        return this->inuqrePay() > that.inuqrePay();
    };

    EmployeeInformation acquireInformation() const
    {
        EmployeeInformation information;
        information.identity = identity;
        information.payrateHour = payrateHour;
        information.name = name;

        return information;
    };

    EmployeeWorkHour acquireWorkHour() const
    {
        EmployeeWorkHour workHour;
        workHour.identity = identity;
        workHour.hour = hour;

        return workHour;
    };

    double inuqrePay() const
    {
        return hour * payrateHour;
    }

    string inquirePayroll() const
    {
        string pay = to_string(inuqrePay());
        return name + ", $" + pay.erase(pay.find_last_not_of('0'));
    };

    void addHour(int input)
    {
        cout << MESSAGE_PREFIX << name << " add " << input << " hours" << endl;
        hour += input;
    };

    friend ostream &operator<<(ostream &output, const Employee &main)
    {
        output << main.inquirePayroll();
        return output;
    };
};

template <class T>
class Node
{
    template <class U>
    friend class LinkedList;

private:
    T data;
    Node<T> *next;

public:
    Node(T data) : data(data), next(nullptr){};
    Node(T data, Node<T> *next) : data(data), next(next){};

    Node<T> *inquireNext() const
    {
        return next;
    }

    T &inquireData()
    {
        return data;
    }
};

template <class T>
class LinkedList
{
private:
    Node<T> *head;

    LinkedList<T> *copyCircular(LinkedList<T> *rightHandSide)
    {
        if (rightHandSide == nullptr)
            return nullptr;
        else
            return new Node(rightHandSide->data, copyCircular(rightHandSide->next));
    };

public:
    LinkedList() : head(nullptr){};

    LinkedList(const LinkedList &rightHandSide) : head(nullptr)
    {
        *this = rightHandSide;
    };
    LinkedList<T> &operator=(const LinkedList<T> &rightHandSide)
    {
        *head = *rightHandSide.head;
        return *this;
    };
    ~LinkedList()
    {
        clear();
    }

    void clear()
    {
        Node<T> *cache = head;
        while (cache->next != nullptr)
        {
            Node<T> *discard = cache;
            cache = cache->next;
            delete discard;
        }
    }

    bool inquireEmpty() const
    {
        return head == nullptr;
    }

    int inquireSize() const
    {
        int count = 0;
        Node<T> *cache = head;
        while (cache != nullptr)
        {
            count++;
            cache = cache->next;
        }
        return count;
    }

    Node<T> *inquireHead() const
    {
        return head;
    }

    void addHead(T item)
    {
        Node<T> *node = new Node<T>(item);
        if (inquireEmpty())
        {
            head = node;
            return;
        }
        else
        {
            node->next = head;
            head = node;
        }
    }

    void addAfter(Node<T> *previous, T item)
    {
        if (previous == nullptr)
            return;
        Node<T> *node = new Node<T>(item);
        node->next = previous->next;
        previous->next = node;
    }

    void addEnd(T item)
    {
        Node<T> *node = new Node<T>(item);
        if (inquireEmpty())
        {
            addHead(item);
            return;
        };

        Node<T> *cache = head;
        while (cache->next != nullptr)
            cache = cache->next;
        cache->next = node;
    };

    void removeFirst()
    {
        if (inquireEmpty())
            return;
        Node<T> *cache = head;
        head = head->next;
        delete cache;
    }

    void removeLast()
    {
        if (inquireEmpty())
            return;
        Node<T> *cache = head;
        while (cache->next != nullptr)
            cache = cache->next;
        delete cache;
    }
};

void merge(vector<Employee> listOfData, int indexOfStart, int indexOfMiddle, int indexOfEnd)
{
    vector<Employee> listOfMerged;
    listOfMerged.reserve(listOfData.size());

    int indexOfLow = indexOfStart;
    int indexOfHigh = indexOfMiddle + 1;
    int indexOfMerged = 0;

    while (indexOfLow <= indexOfMiddle && indexOfHigh <= indexOfEnd)
    {
        if (listOfData[indexOfLow] > listOfData[indexOfHigh])
        {
            listOfMerged[indexOfMerged] = listOfData[indexOfLow];
            indexOfLow++;
            indexOfMerged++;
        }
        else
        {
            listOfMerged[indexOfMerged] = listOfData[indexOfHigh];
            indexOfHigh++;
            indexOfMerged++;
        }
    }

    while (indexOfLow <= indexOfMiddle)
    {
        listOfMerged[indexOfMerged] = listOfData[indexOfLow];
        indexOfLow++;
        indexOfMerged++;
    }

    while (indexOfHigh <= indexOfEnd)
    {
        listOfMerged[indexOfMerged] = listOfData[indexOfHigh];
        indexOfHigh++;
        indexOfMerged++;
    }

    for (int i = 0, j = indexOfStart; i < listOfData.size(); i++, j++)
    {
        listOfData[j] = listOfMerged[i];
    }
};

void sortMerge(vector<Employee> listOfData, int indexOfStart, int indexOfEnd)
{
    if (indexOfStart == indexOfEnd)
    {
        return;
    }
    else
    {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        sortMerge(listOfData, indexOfStart, indexOfMiddle);
        sortMerge(listOfData, indexOfMiddle + 1, indexOfEnd);
        merge(listOfData, indexOfStart, indexOfMiddle, indexOfEnd);
    }
}

void reverse(vector<Employee> &listOfData)
{
    int indexOfStart = 0;
    int indexOfEnd = listOfData.size() - 1;
    while (indexOfStart < indexOfEnd)
    {
        Employee cache = listOfData[indexOfStart];
        listOfData[indexOfStart] = listOfData[indexOfEnd];
        listOfData[indexOfEnd] = cache;
        indexOfStart++;
        indexOfEnd--;
    }
};

int main()
{
    LinkedList<Employee> listOfEmployee;

    ifstream input;
    fileRead(input, "Please enter the filename for employ information:");
    EmployeeInformation information;
    while (input >> information.identity >> information.payrateHour)
    {
        input.ignore(10000, ' ');
        getline(input, information.name);
        Employee employee(information);
        listOfEmployee.addEnd(employee);
    }
    input.close();

    fileRead(input, "Please enter the filename for work hour:");
    EmployeeWorkHour workHour;
    while (input >> workHour.identity >> workHour.hour)
    {
        Node<Employee> *cache = listOfEmployee.inquireHead();
        while (cache != nullptr)
        {
            if (cache->inquireData() == workHour.identity)
                cache->inquireData().addHour(workHour.hour);

            cache = cache->inquireNext();
        }
    };
    input.close();

    cout << endl;
    cout << TITLE_PAYROLL_TOP << endl;
    vector<Employee> listOfData;
    Node<Employee> *cache = listOfEmployee.inquireHead();
    while (cache != nullptr)
    {
        listOfData.push_back(cache->inquireData());
        cache = cache->inquireNext();
    }
    sortMerge(listOfData, 0, listOfData.size() - 1);
    reverse(listOfData);
    for (Employee item : listOfData)
        cout << item << endl;
    cout << TITLE_PAYROLL_BOTTOM << endl;
    cout << endl;

    char answer;
    cout << "Do you want to export the result to output.txt? (Y/N)" << endl;
    cin >> answer;
    ofstream output("output.txt");
    switch (answer)
    {
    case 'Y':
        output << TITLE_PAYROLL_TOP << endl;
        for (Employee item : listOfData)
            output << item << endl;
        output << TITLE_PAYROLL_BOTTOM << endl;
        output.close();
        cout << "Result has been exported. Program ends." << endl;
        exit(0);
    case 'N':
        cout << "Program ends." << endl;
        exit(0);
    default:
        cout << "Illegal character. The program ends automatically." << endl;
        exit(1);
    }

    return 0;
}