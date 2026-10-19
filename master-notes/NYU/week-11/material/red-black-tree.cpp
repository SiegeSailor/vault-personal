// 跟 avl-tree 一樣用旋轉解決，但差別在於旋轉的時機
// 因為 avl-tree 得經常計算或儲存著 height
// red-black tree 有以下條件
// 1. 每個點都是 black or red
// 2. red 點後不能接 red (每個 red 的 left 和 right 都必須為黑)
// 3. root 一定是 black
// 4. 從 root 到最尾端的點，中間的 black node 數必須相同
// 若不能符合以上幾點，則代表該旋轉了。由以上可知，red-black tree 的目的在於平均接上，以及減少計算 height 和 rotation