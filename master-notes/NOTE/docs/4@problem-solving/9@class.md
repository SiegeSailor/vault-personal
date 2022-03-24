---
sidebar_position: 9
---

# Class

中文翻譯類別。一種藍圖、範本，而不是實體化的 Object。不能直接執行，但藉由 Class 出來的 Object 能夠保證規範運作。作為 OOP 中的要角，可以認為是一種由使用者自行定義的 Type 如 `int`、`string` 等。使用上，Class 可以提供：

- [封裝](/docs/4@problem-solving/9@class#封裝): **Encapsulation**。將不需要在 Class 運作以外的資訊透過直觀的 Method (Member Function) 供實體物件使用，並對除了該 Class 外的對象 (即使是子類別) 隱藏，藉此實現 **Information Hiding**
- [繼承](/docs/4@problem-solving/9@class#繼承): **Inheritance**。Class 間可以繼承或參考，藉此堆切出有差異但有統一原則的物件
- [多型](/docs/4@problem-solving/9@class#多型): **Polymorphism**。透過 Interface 實現。在 Method 名稱與 Base Class 皆相同的情況下，使物件做出預期但有差異的行為

(overloading)
(final, friend)

## 封裝

使用 Class 物件時，我們只要知道外顯的 Method 如何使用以及如何實體化。輸入輸出確定的情況下，就算在重構時也不須過多注意內容。可以想成為了實現一個抽象概念，像是一個帳戶，會需要如電力、土地、商標等等基礎設施，最後才到我們平常使用的提領、存款。使用者不需要知道基礎設施的運作、也不應該干涉。

C++ 中，實現封裝一個重要的媒介是 Overloading，主要有 Constructor、Operator、Method：

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
        /** Constructor Overloading */
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
相關概念可以參考 [/Test/Tutorial](/docs/3@test/1@tutorial) 的 Black Box。顧名思義，若一個箱子是黑的，代表內容物不重要、我們只需要知道他是一個箱子，這點與 Encapsulation 的哲學一致。
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
        int total;

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
    public:
        /**
         * 要 Override Base Class 的 Implementation 時，
         * 除了該 Method 在 Base Class 須為 Virtual Function 外，
         * Return Type、Exception Specification 必須相容，
         * 且名稱、Parameter 的規格都必須一致，
         * 若有不同，就只是寫了一個類似的 Method。為了避免這種狀況，
         * Sub Class 可以在欲 Override 的 Method 加上 override，
         * 讓 Compiler 檢查是否一致
         */
        virtual string obtainInterest() const override {
            return "CHF$ " + to_string(interest);
        }
}
```

## 多型

:::info 提醒
相關概念可以參考 [/Test/Unit-Test/Principles/介面相依](/docs/3@test/4@unit-test/5@principles#介面相依)。測試中強調了 **隔離相依性** 的重要，這點可以透過 Polymorphism 的實踐達成。
:::

## 抽象化

ADT:

1. Make all the member variables private members of the class.
2. Make each of the basic operations that the programmer needs a public member function of the class, and fully specify how to use each such public member function.
3. Make any helping functions private member functions.
