#include <iostream>
#include <string>
using namespace std;

// 用 & call by reference 會比較有效率 (因為不用複製)，加上 const 避免手誤更改到值。特別是在 class 內！
// friend Money add(const Money& amount1, const Money& amount2);

class Odd
{
    // 加上 friend，那麼 Date 的 member 內可以使用到 Odd 的 private
    // friend 放 class 任意位置即可，都不影響
    friend class Date;
    // 也可以單指定一個 function
    friend void printOutsideOdd(Odd &);
    // 或指定一個 class 的單個 function，但這邊會噴 Date 尚未定義的錯，理論上應該可行
    // friend void Date::printOdd(Odd &input);

private:
    int total;
};

void printOutsideOdd(Odd &input)
{
    cout << input.total << endl;
};

class Date
{
    friend class NewDate;

    // 基本上不寫的都是默認 private
private:
    int day;
    int month;
    int year;
    // 可以初始給值
    int total = 100;
    int *p;
    // 也可以設 const
    const int a = 10;

protected:
    int p_day;

public:
    // Constructor Overloading，自動選擇符合輸入 parameter 的執行，只有一次
    Date(int y) : year(y), month(0), day(0), p(new int(10)) {}
    // 可以 list 在後面給，或是寫在 {} 內
    Date(int y, int m, int d) : year(y), month(m), day(d), p(new int(10)){
                                                               // 也可以在裡面做計算或指定
                                                               // year = 1994;
                                                               // month = 9;
                                                           };
    string example = "example";

    // 如果 a = b，兩個都是裡面的值有存在 heap (如 new int(1)) 內，
    // 那麼直接相等會導致 a 內的 pointer 指到 b 的，而 a 原本的會沒人只，導致 memory leak
    // 有三大解法可解決，通常要用就是三個一起用
    // BIG 1 destructor，刪除 pointer 值
    ~Date()
    {
        delete p;
    };
    // BIG 2 copy constructor
    Date(const Date &rightHandSide)
    {
        p = new int(*rightHandSide.p);
    };
    // Big 3 assignment constructor (但要避免 a = a，可用記憶體位置 if (this == &rightHandSide) 比對)
    Date &operator=(const Date &rightHandSide)
    {
        *p = *rightHandSide.p;
        // 以下自己加的，但這是 =，所以應該要有值 (Date) 出去
        return *this;
    };

    // 可以重新定義運算子
    Date operator+(const Date &that)
    {
        return Date(this->year + that.year, this->month + that.month, this->day + that.day);
    }
    Date operator-(const Date &that);
    Date operator*(const Date &that);
    Date operator/(const Date &that);
    Date &operator++();
    Date &operator--();
    // 傳入 int 表示為 post-increment (先給再加)，事實上 int 只會傳入一個 0 沒有作用
    Date operator++(int);
    Date operator--(int);

    // member 可以寫在 Class 內
    void setDay(int input)
    {
        if (input >= 1 && input <= 31)
        {
            day = input;
        }
    }
    // member 也可以先定義好後寫在 Class 外
    void setMonth(int input);
    // Mutator
    void setYear(int input)
    {
        if (input >= 1)
        {
            year = input;
        }
    }
    // 加上 const 確保這個 function 不會動到資料 (如果動了編譯會出錯)，而且如果是 const 過的只能用 const function 拿 (return) Accessor
    int getDay() const
    {
        return day;
    }
    int getMonth() const
    {
        return month;
    }
    int getYear() const
    {
        return year;
    }
    // dynamic binding
    virtual void printDate() const
    {
        cout << year << "/" << month << "/" << day << " a: " << a << endl;
    }
    // pure virtual function. const = 0 代表這只會實作在子 class，也代表這個 class 不可直接實體化
    // virtual void speak() const = 0;

    // 因為 Odd 內有宣告 class Date 為 friend，因此可以直接訪問 Odd 的 Private 值
    void printOdd(Odd &input)
    {
        cout << input.total << endl;
    }
};

class NewDate : public Date
{
private:
    int extraDay = 100;

public:
    // 只有這個方法可以使用原本的父 class constructor
    NewDate(int y) : Date(y){};
    NewDate(int y, int m, int d) : Date(y, m, d){};

    void setDay(int input)
    {
        // 即使是 derived class 也不能直接使用父 class private member，所以得透過父的 mutator 做
        Date::setDay(input);

        // 見 Date，將 NewDate 設為 friend 後可以直接用 private member，
        // 但非常不建議 (不然每個子 class 出來都得寫一次)。這種情況下應該用 protected
        cout << day << endl;
        day = 1999;

        p_day = 100;
    }

    NewDate &operator=(const Date);
};

// member 也可以先定義好後寫在 Class 外
void Date::setMonth(int input)
{
    if (input >= 1 && input <= 12)
    {
        this->month = input;
    }
}

int main()
{
    Date date_1(1);
    date_1.printDate();
    date_1.setDay(5);
    date_1.setMonth(10);
    date_1.setYear(2020);
    cout << "date_1:" << endl;
    date_1.printDate();
    int month = date_1.getMonth();
    cout << "Month: " << month << endl;

    Date date_2(2);
    cout << "date_2:" << endl;
    date_2.printDate();
    cout << "date_1 + date_2:" << endl;
    (date_1 + date_2).printDate();

    int a = 0;
    cout << "先給再加 (post-increment)： " << a++ << endl;
    cout << "先加再給 (pre-increment)： " << ++a << endl;

    NewDate new_date_1(1994, 9, 3);
    new_date_1.setDay(25);
    new_date_1.printDate();

    // Polymorphsim
    Date d(1);
    // pointer class 不能給值用於 constructor，但需查為何
    Date *p_d;
    NewDate nd(1);
    NewDate *p_nd;

    d = nd;    // 一定可以，因為 nd 有的 d 一定有 (父)，但 nd 有、d 沒有的都會全部遺失
    nd = d;    // 像是升級，但必須先手動作 Overload operator=(const Date&)
    p_d = &nd; // 父指向子，一定可以。要注意的是，在這時 p_d 呼叫的 function 都是使用 d (base class) 的版本。要用 nd 的版本必須用 dynamic binding 來使用當下指向的版本
    // p_nd = &d; // 一定不行，因為 p_nd 有的 d 不一定有 (最重要是 c++ 不允許 pointer 指向類型不同的值)

    return 0;
}
