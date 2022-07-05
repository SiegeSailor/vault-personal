#include <iostream>
#include <list>
#include <queue>

using namespace std;

// Binary Search Tree 的 Node 可以是 parent-children (array) 或是 parent-child-sibling (linked list)
// Node 的 sub-node 的值不可相等
// Node 的左邊 sub-node 肯定比 Node 小，而右邊肯定比 Node 大
// Search, Insert, Delete 都只花 O(logN)
// 對於 in-order storage 非常有效。只要裡面的值可以用 < 來比較排序
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
        // 以下為 recursion。讓所有 sub node、從 left 和 right 開始，自行算出自己的 size 再加總
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
    // 在輸入的值是 5, 10, 15, ... 的情況下，新的值只會在右邊，那麼 BST 會是 O(N) 而不是 O(logN)，因為這樣就只是 linked list
    void insert(T item)
    {
        if (root == nullptr)
        {
            root = new BSTNode<T>(item);
            return;
        };

        BSTNode<T> *cache = root;
        // cache 會跑到 nullptr，而 previous 就是在找到的 nullptr 前的最後一個 Node
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
        if (target->left == nullptr && target->right == nullptr)
        {
            // BST 中最後一個 node
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

    // Tree traversal
    // in order(正常): left -> this -> right
    void printInOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            printInOrder(node->left);
            cout << node->data << ", ";
            printInOrder(node->right);
        };
    };

    // pre order: this -> left -> right
    void printPreOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            cout << node->data << ", ";
            printInOrder(node->left);
            printInOrder(node->right);
        };
    };

    // post order: left -> right -> this
    void printPostOrder(BSTNode<T> *node)
    {
        if (node != nullptr)
        {
            printInOrder(node->left);
            printInOrder(node->right);
            cout << node->data << ", ";
        };
    };

    // level order (breadth first search): 越靠近 root 的越先 (照 depth 排序)
    // 再來是先 left 再 right
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

int main()
{
    return 0;
}
