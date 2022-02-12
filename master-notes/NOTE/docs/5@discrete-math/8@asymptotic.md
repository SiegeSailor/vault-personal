---
sidebar_position: 8
---

# Asymptotic

當標示演算法的時間複雜度時，會用到 $\mathcal{O}, \Omega, \Theta$ 來表示，經常見到的有 $f=\mathcal{O}(n)$ 或 $f=\Omega(n^{2})$ 等。每種標示都帶有同樣一個目的，即在 $c$ Constant 的乘積與代入的 $n \geq n_{0}$ 前提下，$\geq, \leq, =$ 一個時間複雜度。注意在標示中，所有 $n$ 的 Coefficient (包含 $n^{0}$) 皆不重要，只會關注 $n$ 的最高 Exponent (Degree)。

以下由低至高列出時間複雜度：

1. $1$
2. $\log \log n$
3. $\log n$
4. $n$
5. $n \log n$
6. $n^{2}$
7. $n^{3}$
8. $n^{m}$
9. $c^{n}, c > 1$
10. $n!$

:::info 提醒
對 $\mathcal{O}$ 而言，時間複雜度可以向上或下推導，如 $f=\mathcal{O}(n)$ 可以導出 $f=\mathcal{O}(n^{2})$。$\Omega$ 同理，$f=\Omega(n)$ 可以導出 $f=\Omega(\log n)$
:::

:::caution 注意
$=$ 在這是一個混淆的符號，因為這邊的 $=$ 的用法更像是 $\in$。如 $f=\mathcal{O}(n)$ 實際上等於 $f(n) \leq c \times g(n)$。$g(n)$ 理解為具有同樣時間複雜度的 Function，可以與 $f(n)$ 相同或不同。

$\Omega$ 同理，$f=\Omega(n)$ 等於 $f(n) \geq c \times g(n)$。而 $\theta$ 較不一樣，因為 $\theta$ 內即有 $c_{1}$ ($\mathcal{0}$) 與 $c_{2}$ ($\Omega$)。在這裡作為 $=$ 用，時間複雜度必須一樣才會成立。
:::

## Big Theta

$\Theta$。表示時間複雜度本身，只關注 Function 本身複雜度表示的 Polynomial 的最高 Exponent，也就是 Degree。這邊由如何導出 $f$ 開始，假設有一尋找質數的 Function `isPrime`：

```cpp title="test-primality.cpp"
#include <iostream>
#include <cmath>
using namespace std;

bool isPrime(int input);

int main()
{
    int input;
    cout << "Enter a positive integer: ";
    cin >> input;
    if (input < 1)
        cout << "Please enter a positive integer." << endl;
    else if (isPrime(input))
        cout << input << " is a prime number." << endl;
    else
        cout << input << " is not a prime number." << endl;
    return 0;
};

bool isPrime(int input)
{
    if (input == 1)
        return true;

    for (int i = 2; i <= sqrt(input); i++)
    {
        cout << i << endl;
        if (input % i == 0)
            return false;
    }

    return true;
}
```

### 時間複雜度

只關注 `isPrime` 內的時間複雜度，那麼：

```cpp
bool isPrime(int input)
{
    // highlight-start
    /** a: 比較(input == 1): 1 */
    if (input == 1)
    // highlight-end
        return true;

    // highlight-start
    /** a: 初始值(i): 1 */
    /**
     * b: 執行其他函式(sqrt): 1
     * b: 比較(i <= sqrt(input)): 1
     * b: 計算與賦值(i++): 2
     */
    for (int i = 2; i <= sqrt(input); i++)
    // highlight-end
    {
        // highlight-start
        /**
         * b: 執行其他函示(cout): 1
         * b: 計算(input % i): 1
         * b: 比較(input % i == 0): 1
         */
        cout << i << endl;
        if (input % i == 0)
            // 通常計算時考慮 Worst Case，不設想找到的情況
            return false;
        // highlight-end
    }

    // highlight-start
    /** c: 回傳(return): 1 */
    return true;
    // highlight-end
}
```

`isPrime` 的 $f$ 為 $a + (n - 1) \times b + c$，其中 $b$ 部分的之所以乘上 $n-1$ 是因為在 Loop 內、是由 Input Size 決定執行時間，而 $n-1$ 是因為由 2 開始計算，即：

$$
(1 + 1) + ((n - 1) \times (1 + 1 + 2 + 1 + 1 +1)) + 1 = 2 + 7n - 7 + 1 = 7n - 4
$$

在當中的 Degree 為 1，因此 $7n - 4 = \Theta(n)$。由於 $-4$ 為負數，可以推斷出：

$$
7n - 4 \leq 7n \\
6n + (n - 4) \leq 7n - 4 \\
n - 4 \geq 0 \\
6n \leq 7n - 4 \\
\therefore c_{1} = 7, c_{2} = 6, n_{0} = 4
$$

## Big O

$\mathcal{O}$。Upper Bound。表示演算法的時間上限，這也是演算法之間比較執行速度時使用的定義：

- $c$: 所有 Function 內 Positive Coefficient 的總和
- $n_{0}$: 1

## Big Omega

$\Omega$。Lower Bound。表示演算法的時間下限。無 Negative Coefficient 的情況下：

- $c$: 假設為 $\Omega(n^{k})$，取 Function 內 $n^{k}$ 的 Coefficient，稱為 $a_{k}$
- $n_{0}$: 1

有 Negative Coefficient 的情況下。A 為所有 Negative Coefficient 總和的絕對值：

- $c$: $a_{k}$/2
- $n_{0}$: 2A/$a_{k}$

:::caution 注意
無論何種情況，$a_{k}$ 皆不可為負數。
:::
