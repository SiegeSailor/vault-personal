def merge_sort(list_integer, index_start, index_end):
    if index_start != index_end:
        index_middle = round((index_start + index_end) / 2)
        merge_sort(list_integer, index_start, index_middle)
        merge_sort(list_integer, index_middle + 1, index_end)
        merge(list_integer, index_start, index_middle, index_end)

def merge(list_integer, index_start, index_middle, index_end):
    list_sorted_integer = [0] * (index_end - index_start + 1)
    pointer_left = index_start
    pointer_right = index_middle + 1
    pointer = 0
    while pointer_left <= index_middle and pointer_right <= index_end:
        if list_integer[pointer_left] <= list_integer[pointer_right]:
            list_sorted_integer[pointer] = list_integer[pointer_left]
            pointer_left += 1
        else:
            list_sorted_integer[pointer] = list_integer[pointer_right]
            pointer_right += 1
        pointer += 1
    
    while pointer_left <= index_middle:
        list_sorted_integer[pointer] = list_integer[pointer_left]
        pointer_left += 1
        pointer += 1
    
    while pointer_right <= index_end:
        list_sorted_integer[pointer] = list_integer[pointer_right]
        pointer_right += 1
        pointer += 1
    
    for index_sorted in range(0, len(list_sorted_integer)):
        list_integer[index_start] = list_sorted_integer[index_sorted]
        index_start += 1

list_unsorted = [3, 5, 1, 3, 9, 0, 2]
merge_sort(list_unsorted, 0, len(list_unsorted) - 1)
print(list_unsorted)