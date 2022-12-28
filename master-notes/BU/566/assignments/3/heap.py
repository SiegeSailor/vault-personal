from math import floor


def heapify_max(list_integer,  index):
    index_left = (index * 2) + 1
    index_right = index_left + 1
    index_largest = -1

    if index_left <= len(list_integer) - 1 and list_integer[index_left] > list_integer[index]:
        index_largest = index_left
    else:
        index_largest = index

    if index_right <= len(list_integer) - 1 and list_integer[index_right] > list_integer[index_largest]:
        index_largest = index_right

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