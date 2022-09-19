import numpy as number
import matplotlib.pyplot as plot
import matplotlib.colors as colors

def generate_plot(size_x: int) -> None:
    scale_x = number.arange(0, size_x, 0.1)
    list_plot = [
        {
            'scale_y': (pow(2, 10) * scale_x) + pow(2, 10),
            'color': colors.BASE_COLORS['r']
        },
        {
            'scale_y': pow(scale_x, 3.5) - 1000,
            'color': colors.BASE_COLORS['b']
        },
        {
            'scale_y': (100 * pow(scale_x, 2.1)) + 50,
            'color': colors.BASE_COLORS['g']
        }
    ]
    for item in list_plot:
        plot.plot(scale_x, item['scale_y'], color=item['color'])

    plot.title(f'Size X: {size_x}')
    plot.show();

for x in [5, 15, 50]:
    generate_plot(x)