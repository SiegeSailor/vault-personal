# Methods

## Methods

Common methods.

#### Check if a Key Exists

Use this to check if there is the key.

```python
if result['apiURL'] is None:
    return
```

Or check if a key is in a dictionary.

```python
if "test" in wordFreqDic:
    return
```

```python
if "key" not in request.session:
    return
```

#### Appending a Value

```python
d['stupdid'].append(foo)
```

#### Wipe Empty Character

This method allows you to wipe empty character around a string.

```python
a = " abec "
len(a)
>>> 6
len(a.strip())
>>> 3
```

