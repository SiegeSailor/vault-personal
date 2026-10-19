---
sidebar_position: 22
---

# Sequential Search

又稱 Linear Search。最基本的搜尋演算法之一，即從頭開始一個個尋找：

- **Worst Case Time Complexity**: $\mathcal{O}(n)$
- **Best Case Time Complexity**: $\Omega(1)$
- **Average Time Complexity**: $\Theta(n)$
- **Space Complexity**: $\Theta(1)$

```cpp
#include <vector>

using namespace std;

int sequentialSearch(vector<int> v, int toFind)
{
	for (int i = 0; i < v.size(); i++)
		if (v[i] == toFind)
			return i;
	return -1;
}
```
