Priority Queue:
Array of priorities mapped to queues
0 > queue(head -> tail)
1 > queue(head -> node -> tail)
2 > queue(head)
3 > queue(null)

Aging:
To avoid starving of tasks, every tak has a timer, bump priority plus one when it reaches.

Array Queue:
indexOfHead, indexOfTail;
0 <empty>
1 3 (head)
2 5
3 <empty> (tail, always empty)
Circular! When we remove head
0 <empty>
1 <empty>
2 5 (head)
3 <empty> (tail, always empty)
Insert one
0 <empty> (tail, always empty)
1 3 (head)
2 5
3 7
