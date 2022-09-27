```python
def add_binary_list(list_a, list_b):
  list_result = [None for i in range(len(list_a) + 1)]
  carry = 0
  for index in range(len(list_a) - 1, -1, -1):
    list_result[index + 1] = (list_a[index] + list_b[index] + carry) % 2
    carry = int((list_a[index] + list_b[index] + carry) / 2)

  list_result[0] = carry
  return list_result

print(add_binary_list([0,1,0,1], [1,0,1,1]))
```
