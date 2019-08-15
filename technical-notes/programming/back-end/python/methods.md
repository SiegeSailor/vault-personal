# Methods

## Overview

Common methods.

## Check If A Key Exists

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

## Appending A Value

```python
d['stupdid'].append(foo)
```

## Wipe Empty Character

This method allows you to wipe empty character around a string.

```python
a = " abec "
len(a)
>>> 6
len(a.strip())
>>> 3
```

## Round Alternative

Usually we use round\(\) to get point numbers, but the method is not always rendering the right number we want, so here is another solution.

```python
real_time_score = int('0.555')
real_time_score = '%.1f' % real_time_score
```

`%.1f` can be `%.2f` or `%.3f`, depends on how many point numbers you want to have.

{% hint style="info" %}
See [Here](https://blog.csdn.net/chenmozhe22/article/details/81666831).
{% endhint %}

## Get Related Value with a Key

To compare a key's value to a key's value from a dict with `next`, then get or replace the value from the dict under the key.

```python
for indexOuter in range(len(response['data'])):
        for indexInner in range(len(response['data'][indexOuter]['replies'])):
            response['data'][indexOuter]['replies'][indexInner]['question'] = (next(
                item for item in request.session['questions']['data'] if item["id"] == response['data'][indexOuter]['replies'][indexInner]['question_id']))['question']
            response['data'][indexOuter]['replies'][indexInner]['question_des'] = (next(
                item for item in request.session['questions']['data'] if item["id"] == response['data'][indexOuter]['replies'][indexInner]['question_id']))['question_des']
```

