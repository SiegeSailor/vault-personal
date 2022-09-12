from typing import List
import numpy as number
import matplotlib.pyplot as plot

def function_1(input: int) -> int:
    return (pow(2, 10) * input) + pow(2, 10)

def function_2(input: int) -> float:
    return pow(input, 3.5) - 1000

def function_3(input: int) -> float:
    return (100 * pow(input, 2.1)) + 50

def create_x_axis(limit: int) -> List[int]:
    return number.linspace(-limit, limit, num=1000)

list_setup = [
    {
        'function': function_1,
        'x': 5,
        'color': 'red',
        'label': 'f1(n)'
    },
    {
        'function': function_2,
        'x': 15,
        'color': 'blue',
        'label': 'f2(n)'
    },
    {
        'function': function_3,
        'x': 50,
        'color': 'green',
        'label': 'f3(n)'
    }
]
for setup in list_setup:
    plot.plot(create_x_axis(setup['x']), setup['function'](create_x_axis(setup['x'])), \
        color=setup['color'], label=setup['label'])

plot.xlabel('x')
plot.ylabel('y')
plot.show()