---
sidebar_position: 8
---

# Binary Search Tree

首先 Binary Search Tree 由 **Binary Tree** 而來、而 Binary Tree 又由 **Tree** 而來。在 Binary Tree 與 Tree 的特性上，又有以下：

- Left Sub Node 的值 `<` Right Sub Node 且不可相等
- `insert`、`delete` 和 `search` 的 Time Complexity 皆為 $\Theta(\log n)$
- 對於 In-Order Storage 非常有效 (裡面的值必須可以用 `<` 來比較)

Binary Search Tree 可以用 **Array** (Parent-Children) 或 **Linked List** (Parent-Child-Sibling) 實作，以下使用自造的 Linked List：

:::caution 注意
若插入的值永遠更大，新的值只會存在右邊，`insert`, `delete`, `search` 會是 $\Theta(n)$ 而不是 $\Theta(\log n)$，因為只往單一邊 Insert 的情況下、形成的只會是一般的 Array 或 Linked List。
:::

```cpp
#include <iostream>
#include <queue>

using namespace std;

template <class T>
class BSTNode
{
    template <class U>
    friend class BST;

private:
    T data;
    BSTNode<T> *parent;
    BSTNode<T> *left;
    BSTNode<T> *right;

public:
    BSTNode(T data = T(), BSTNode<T> *parent = nullptr, BSTNode<T> *left = nullptr, BSTNode<T> *right = nullptr)
        : data(data), parent(parent), left(left), right(right) {}
    int size() const
    {
        int count = 1;
        /** Recursion。讓所有 Sub Node 由 Left 和 Right 開始，自行算出 Size 再加總 */
        if (left != nullptr)
            count += left->size();
        if (right != nullptr)
            count += right->size();

        return count;
    };
};

template <class T>
class BST
{
private:
    BSTNode<T> *root;

public:
    void insert(T item)
    {
        if (root == nullptr)
        {
            root = new BSTNode<T>(item);
            return;
        };

         /**
          * 結束條件為 cache == nullptr，
          * 會一直向下找到最底的 Leaf。
          * 當到達最底時，previous 會是 Leaf 的 Parent
          */
        BSTNode<T> *cache = root;
        BSTNode<T> *previous = root;
        while (cache != nullptr)
        {
            previous = cache;
            if (item < cache->data)
                cache = cache->left;
            else
                cache = cache->right;
        };

        if (item < previous->data)
            previous->left = new BSTNode<T>(item, previous);
        else
            previous->right = new BSTNode<T>(item, previous);
    };

    void remove(BSTNode<T> *&target)
    {
        // 當要移除的是 Leaf (無 sub node) 時，只需直接移除、然後更新他的 parent 指向到 nullptr
        /**  */
        if (target->left == nullptr && target->right == nullptr)
        {
            /** */
            if (target->parent == nullptr)
            {
                root = nullptr;
            }
            else if (target->parent->left == target)
            {
                target->parent->left = nullptr;
            }
            else
            {
                target->parent->right = nullptr;
            }

            delete target;
        }
        // 當要除的 Node 只有ㄧ child 時，只需將要除的 Node 的 parent 指向 child 即可，因為 child 和該 Node 對於整個 Tree 來說的特性是一樣的 (值的大小與順序)
        // 當只有 right child 時
        else if (target->left == nullptr)
        {
            // 將 right 往上移一層。這邊應該可以用 operator 和 big 3 做
            BSTNode<T> *toDelete = target->right;
            target->data = toDelete->data; // 其實就是原本的 target->right->data
            target->left = toDelete->left; // target->right->left
            // 將 right 的 children 的 parent 接到要刪除的 node 的位置 (現已被要刪除的 node 的 right 代替)
            if (target->left != nullptr)
                target->left->parent = target;
            target->right = toDelete->right;
            if (target->right != nullptr)
                target->right->parent = target;

            // 為什麼不直接把 target->parent->right 指向 target->right、然後再刪除 target?
            // target->parent->

            delete toDelete;
        }
        // 當只有 left child 時
        else if (target->right == nullptr)
        {
            BSTNode<T> *toDelete = target->left;
            target->data = toDelete->data;
            target->left = toDelete->left;
            if (target->left != nullptr)
                target->left->parent = target;
            target->right = toDelete->right;
            if (target->right != nullptr)
                target->right->parent = target;

            delete toDelete;
        }
        else
        {
            BSTNode<T> *minOfRight = target->right;
            while (minOfRight->left != nullptr)
                minOfRight = minOfRight->left;
            target->data = minOfRight->data;
            // 雖然這邊是 recursion，但只會再發生一次。也就是將要刪除的 node 用 right 下方左邊最小的值替換掉 (第一次)，然後再帶入、用只有單一 node 的情況的 remove 刪除最小值的 node (第二次)
            remove(minOfRight);
        }
    }

    /**
     * Tree Traversal: In Order
     * Left -> This -> Right。
     */
    void printInOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            printInOrder(node->left);
            cout << node->data << ", ";
            printInOrder(node->right);
        };
    };

    /**
     * Tree Traversal: Pre Order
     * This -> Left -> Right。
     */
    void printPreOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            cout << node->data << ", ";
            printInOrder(node->left);
            printInOrder(node->right);
        };
    };

    /**
     * Tree Traversal: Post Order
     * Left -> Right -> This。
     */
    void printPostOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            printInOrder(node->left);
            printInOrder(node->right);
            cout << node->data << ", ";
        };
    };

    /**
     * Tree Traversal: Level Order
     * Breadth First Search，越靠近 Root 的越先 (照 Depth 排序)，
     * 再來是 Left，接著 Right。
     */
    void printLevelOrder()
    {
        queue<BSTNode<T>> q;
        q.push(root);
        while (!q.empty())
        {
            BSTNode<T> *cache = q.front();
            q.pop();
            cout << cache->data << ", ";
            if (cache->left != nullptr)
                q.push(cache->left);
            if (cache->right != nullptr)
                q.push(cache->right);
        }
    };
};
```

## AVL Tree

// 每個左右結點間的高度差不超過 1
// 所謂每個節點的高度差，就是在要計算的點到他的最底部的 leaf 的 edge 的總和
// 當不能符合高度差，就是該旋轉的時候

## Red Black Tree

// 跟 avl-tree 一樣用旋轉解決，但差別在於旋轉的時機
// 因為 avl-tree 得經常計算或儲存著 height
// red-black tree 有以下條件
// 1. 每個點都是 black or red
// 2. red 點後不能接 red (每個 red 的 left 和 right 都必須為黑)
// 3. root 一定是 black
// 4. 從 root 到最尾端的點，中間的 black node 數必須相同
// 若不能符合以上幾點，則代表該旋轉了。由以上可知，red-black tree 的目的在於平均接上，以及減少計算 height 和 rotation
