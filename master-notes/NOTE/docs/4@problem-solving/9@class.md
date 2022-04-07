---
sidebar_position: 9
---

# Class

中文翻譯類別。一種藍圖、範本，而不是實體化的 Object。不能直接執行，但藉由 Class 出來的 Object 能夠保證規範運作。作為 OOP 中的要角，可以認為是一種由使用者自行定義的 Type 如 `int`、`string` 等。使用上，Class 可以提供：

- [封裝](/docs/4@problem-solving/9@class#封裝): Encapsulation。將不需要在 Class 運作以外的資訊透過直觀的 Method (Member Function) 供實體物件使用，並對除了該 Class 外的對象 (即使是子類別) 隱藏，藉此實現 **Information Hiding**
- [繼承](/docs/4@problem-solving/9@class#繼承): Inheritance。Class 間可以繼承或參考，藉此堆切出有差異但有統一原則的物件
- [多型](/docs/4@problem-solving/9@class#多型): Polymorphism。透過 Interface 實現。在 Method 名稱與 Base Class 皆相同的情況下，使物件做出預期但有差異的行為

## 封裝

使用 Class 物件時，我們只要知道外顯的 Method 如何使用以及如何實體化。輸入輸出確定的情況下，就算在重構時也不須過多注意內容。可以想成為了實現一個抽象概念，像是一個帳戶，會需要如電力、土地、商標等等基礎設施，最後才到我們平常使用的提領、存款。使用者不需要知道基礎設施的運作、也不應該干涉：

```cpp
class Account {
    private:
        int day;
        double interest;
        int saving;
        int balance;

        void increaseBalance(){
            balance += interest * saving * day;
        }

    public:
        Account() : {
            day = 1;
            interest = 1.0;
            saving = 0;
        };
        /** 建構時使用 Call By Reference，顯著提升效能，並且使用 const 避免誤改 */
        Account(const int &inputSaving) : dat(1), interest(1.0) ,saving(inputSaving) {};

        /** Mutator */
        int pass(){
            increaseBalance();
            day++;
        }

        /** Accessor，注意 const 在宣告後代表此 Method 不會對 Member Variable 做更動 */
        double obtainDay() const {
            return day;
        };
}

int main()
{
    Account account;
    /** 可以使用 Public Method 來更動 Member Variable */
    account.pass();

    /** 但不可直接更動 Private Variable，甚至連取值都無法 */
    account.day++;
    return 0;
}
```

:::info 提醒
相關概念可以參考 [/Test/Tutorial](/docs/8@test/1@tutorial) 的 Black Box。顧名思義，若一個箱子是黑的，代表內容物不重要、我們只需要知道他是一個箱子，這點與 Encapsulation 的哲學一致。
:::

## 繼承

自己的銀行帳戶是特別的，但在背後，與其他帳戶有共通的規則，而帳戶的定義又與金融有共通規則。一層層繼承，OOP 大多以 Class 實現這種關係。尤其是 Method 間的關係，C++ 以 `virtual` 定義不同的繼承關係：

- **Pure Virtual Method**: 只繼承介面 Interface
- **Virtual Method**: 繼承介面並且預設實現 **Default Implementation**
- **Method**: 繼承介面並且強制實現 **Mandatory Implementation**

以下延續 Class Account 舉例：

```cpp
#include <string>

using namespace std;

/** 若宣告 Account 為 class Account final {}，則不可被繼承 */
class Account {
    /** 定義為 Private 的 Variable 除了不能在 Class Scope 外使用，即使是 Sub Class 也不可取用 */
    private:
        ...
        int total = 0;

    /** Protected Variable 與 Private Variable 類似，差別在於 Sub Class 可以直接取用 */
    protected:
        string country = "Swiss";

    public:
        /**
         * 一般 Method，Sub Class 可以重新定義，
         * 但只會根據 Compile 時該物件的 Type 選擇要執行 Base Class 或 Sub Class 的 Method。
         * 舉例而言，有個物件是 Pointer，在宣告時使用 Base Class、後續指向 Sub Class
         * 使用時只會使用到 Base Class 的 Method，
         * 這是因為一般 Method 是 Static Binding，編譯即定義
         */
        void announce(string message) const {
            cout << "Message: " << message << endl;
        }

        /** virtual 代表 Dynamic Binding，子類別有實作的情況下會優先使用該子類別下的同名 Method */
        virtual string obtainInterest() const {
            return "NT$ " + to_string(interest);
        }

        /**
         * 同 Class，若 Virtual Method 不想被覆寫，則可加上 final，
         * 可預防意外覆寫的情況
         */
        virtual void callCopyright const final {
            cout << "Copyright 2022" << endl;
        }

        /**
         * virtual 後加上 const = 0 表示此為 Pure Virtual Method，
         * 每個子類別都必須實作 Implementation，且此 Base Class Account 不可實體化
         */
        virtual string obtainDay() const = 0;

        /**
         * Pure Virtual Method 仍然可以在 Base Class 實作、
         * 強制要求 Sub Class 實作，
         * 並透過 this->Account::obtainDay 使用，
         * 但為 Non-Standard，不建議
         */
        virtual string obtainDay(int base) const = 0 {
            return "Day: " + to_string(day + base);
        };
}

/**
 * 繼承表示可以取用 Base Class 在 Public 和 Protected 中的資料，
 * 唯因應繼承方法不同，對應到 Sub Class 的層級也不同：
    - Public:
        protected >> protected
        public >> public
    - Protected:
        protected >> protected
        public >> protected
    - Private:
        protected >> private
        public >> private
 */
class SwissBankAccount : public Account {
    private:
        /** 此行不會有影響，SwissBankAccount 建立的物件的 total 仍然會是 0 */
        int total = 100;

    public:
        /**
         * 要 Override Base Class 的 Implementation 時，
         * 除了該 Method 在 Base Class 須為 Virtual Function 外，
         * Return Type、Exception Specification 必須相容，
         * 且名稱、Parameter 的規格都必須一致，
         * 即同樣的 Signature (除了 const 與 & 外的部分)，
         * 若有不同，就只是寫了一個類似的 Method (Redefinition)。為了避免這種狀況，
         * Sub Class 可以在欲 Override 的 Method 加上 override，
         * 讓 Compiler 檢查是否一致
         */
        virtual string obtainInterest() const override {
            return "CHF$ " + to_string(interest);
        }
}
```

## 多型

最主要的概念是，被用作 Parameter 的 Class 維持外顯的 Method 不變的情況下，可以直接替換內容：

```cpp
#include <iostream>
#include <vector>

using namespace std;

class Base
{
    public:
        Base() {}
        virtual void call() {
            cout << "BASE ";
        }
};

    class Derived: public Base {
    public:
        Derived(): Base() {}
        virtual void call {
            cout << "DERIVED ";
        }
};

int main() {
    vector<Base> listOfObject;
    Base first = Derived();
    Derived second = Derived();
    Base third = Base();
    listOfObject.push_back(first);
    listOfObject.push_back(second);
    listOfObject.push_back(third);

    /**
     * 以下輸出會是： BASE BASE BASE BASE DERIVED BASE，
     * 注意當下呼叫 call 的物件的型別。像是放入 listOfObject 中、
     * 由於 vector 為 Base，因此所有被放入的 Derived 都被強制轉為 Base。
     * 被強制轉換時，所有 Base 沒有而 Derived 有的都會遺失
     */
    // highlight-start
    for (Base object : listOfObject) {
        object.call();
    }
    first.call();
    second.call();
    third.call();
    // highlight-end

    Base baseNormal;
    *Base basePointer;
    Derived derivedNormal;
    *Derived derivedPointer;

    /**成立，因為 Base 有的 Derived 一定有，然而 Derived 有但 Base 沒有的都會遺失 */
    baseNormal = derivedNormal;
    /**
     * 成立，但須先建立 Overload Operator Assignment Constructor
     * Derived &operator=(const Base&rightHandSide){ return *this; }
     */
    derivedNormal = baseNormal;
    /**
     * 成立，但要注意所使用 Method 都是 Base 版本，
     * 除非使用 Dynamic Binding
     */
    basePointer = &derivedNormal;
    /** 失敗，因為 Derived 有的 Base 不一定有，還有 C++ 不允許 Pointer 指向不同的值 */
    derivedPointer = &baseNormal;

    return 0;
}
```

:::info 提醒
相關概念可以參考 [/Test/Unit-Test/Principles/介面相依](/docs/8@test/4@unit-test/5@principles#介面相依)。測試中強調了 **隔離相依性** 的重要，這點可以透過 Polymorphism 的實踐達成。
:::

## Other

除了以上共通的三大要點**封裝**、**繼承**和**多型**外，C++ Class 還有幾項需要特別注意的點。

### Abstract Data Type

抽象化，簡稱 ADT。將 Class 外顯的 Function 以 Interface 的概念實作，使用者不需關心背後的邏輯與值如何運作。由於隱藏了細節、只公開介面，在未來更改 (特別牽涉到多型時) 能以更低的成本進行。要符合 ADT，有以下須遵循：

1. 所有 Member Variable 皆為 Private
2. 外顯的 Public Method 為簡單邏輯操作，並且無論在語意或文件上皆能完整指引
3. 背後的細節與邏輯操作 (Helper) 以 Private Method 完成

### Overloading

一個大部分語言未實踐、C++ 卻有的功能是 Overloading，能夠對物件的 Operator 做更動，主要用在 Constructor、Operator、Method：

```cpp
#include <string>

using namespace std;

class Account {
    private:
        int day;
        double interest;
        int saving;
        int balance;

    public:
        /** Constructor Overloading */
        Account() {
            day = 1;
            interest = 1.0;
            saving = 0;
        };
        Account(const int &saving)
            : day(1), interest(1.0) ,saving(saving) {};
        Account(const int &day, const int &saving)
            : day(day) {
            interest = 2.0;
        };

        /** Operator Overloading */
        Account operator-(const Account &that);
        Account operator*(const Account &that);
        Account operator/(const Account &that);
        /** Pre-Increment (先加再給) */
        Account &operator++();
        Account &operator--();
        /** 傳入 int 表示為 Post-Increment (先給再加)，事實上 int 只會傳入一個 0，並沒有作用 */
        Account operator++(int);
        Account operator--(int);

        /** Method Overloading */
        void message()
        {
            cout << "無 Parameter。" << endl;
        };

        void message(int input)
        {
            cout << "有 Parameter。" << endl;
        };
};
```

:::info 提醒
Struct 內也能使用 Overloading：

```cpp
struct Coordinate
{
    int x = 0;
    int y = 0;
    bool operator!=(const Coordinate &that)
    {
        return x != that.x || y != that.y;
    }
    Coordinate operator=(const Coordinate &that)
    {
        x = that.x;
        y = that.y;
        return *this;
    }
};
```

:::

### Rule of Three

又稱 Big 3。是在 Class 內有 Pointer Member Variable 時必須三項同時應用的規則，主要是避免在 Assign、Copy、Destruct 三個動作中、遺失對 Pointer 的控制，進而導致 Memory Leak 的情況：

```cpp
#include <string>

using namespace std;

class Book
{
    private:
        int *currentPage;
        int maxSizeOfTitle = 0;
        char *title = "\0";

    public:
        /** 注意此 Class 有 Member Variable 存放在 Heap 中 */
        Book(int currentPage) : currentPage(new int(currentPage)) {}

        /**
         * Copy Constructor，在以下情況中執行：
         * Book bookFirst(10);
         * Book bookSecond(bookFirst);
         * 主要避免已有的 Pointer 遺失指向
         */
        Book(const Book &rightHandSide)
        {
            currentPage = new int(*rightHandSide.currentPage);
            /** 注意不可使用以下，會導致雙方指向的變數相同，一改兩者皆動 */
            currentPage = rightHandSide.currentPage;

            maxSizeOfTitle = rightHandSide.maxSizeOfTitle;

            title = new char(*rightHandSide.title);
        }

        /**
         * Assignment Constructor，在以下情況中執行：
         * Book bookFirst, bookSecond;
         * bookSecond = bookFirst;
         * 注意與 Copy Constructor 的不同之處
         */
        Book &operator=(const Book &rightHandSide)
        {
            *currentPage = *rightHandSide.currentPage;

            int lengthOncoming = strlen(rightHandSide.title);
            if (lengthOncoming > maxSizeOfTitle)
            {
                delete [] title;
                maxSizeOfTitle = lengthOncoming;
                title = new char[maxSizeOfTitle + 1];
            };
            for (int i = 0; i < lengthOncoming; i++)
                title[i] = rightHandSide.title[i];
            title[lengthOncoming] = '\0';

            return *this;
        };

        /**
         * Destructor，在此 Class 被釋放時執行。
         * 注意若沒有 delete，則 currentPage 在 Book 被釋放仍存在 Heap 中，
         * 但沒有任何對應的方法去操控
         */
        ~Book()
        {
            delete currentPage;
            delete title;
        }
};
```
