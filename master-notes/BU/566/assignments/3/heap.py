from math import floor


def heapify_max(list_integer,  index):
    index_left_child = (index * 2) + 1
    index_right_child = index_left_child + 1
    index_largest = index

    if index_left_child <= len(list_integer) - 1 and list_integer[index_left_child] > list_integer[index]:
        index_largest = index_left_child

    if index_right_child <= len(list_integer) - 1 and list_integer[index_right_child] > list_integer[index_largest]:
        index_largest = index_right_child

    if list_integer[index] < list_integer[index_largest]:
        cache = list_integer[index_largest]
        list_integer[index_largest] = list_integer[index]
        list_integer[index] = cache

        heapify_max(list_integer, index_largest)


def build_heap_max(list_integer):
    for index in range(floor(len(list_integer) / 2), -1, -1):
        heapify_max(list_integer, index)


list_integer = [1, 18, 5, 20, 30, 19, 20, 2, 25, 4, 27]
heapify_max(list_integer, 1)

print(list_integer)
