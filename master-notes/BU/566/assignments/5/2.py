def knapsack(number_shops , list_city_centers):
    number_city_centers = len(list_city_centers)

    if number_city_centers == 0:
        raise Exception('There is no city centers.')

    if number_shops >= number_city_centers:
        raise Exception('The number of shops should be' \
             'smaller than the number of city centers.')

    list_city_centers_copied = list_city_centers.copy()
    list_city_centers_copied.sort()

    cost = [[0 for _ in range(number_city_centers + 1)] 
        for _ in range(number_city_centers + 1)]

    '''
    number_city_centers: 4

    for 0,1,2,3
        index_city_center: 0
            for 0,1,2,3
                > index_line: 0
                    middle = (0 + 0) / 2 = 0
                    for 0
                > index_line: 1
                    ...
                > index_line: 2
                    middle = (0 + 2) / 2 = 1
                    for 0,1,2
                > index_line: 3
                    middle = (0 + 3) / 2 = 1
                    for 0,1,2,3
                        >> index_inner: 0
                            delta = list_city_centers[0] - list_city_centers[1] = 1 - 2 = -1
                            cost[0 + 1][3 + 1] += delta (set to positive as no negative distance)
                        >> index_inner: 1
                            delta = list_city_centers[1] - list_city_centers[1] = 2 - 2 = 0
                            cost[0 + 1][3 + 1] += delta
                        >> index_inner: 2
                            delta = list_city_centers[2] - list_city_centers[1] = 3 - 2 = 1
                            cost[0 + 1][3 + 1] += delta
                        >> index_inner: 3
                            delta = list_city_centers[3] - list_city_centers[1] = 6 - 2 = 4
                            cost[0 + 1][3 + 1] += delta
                        
                        const[0 + 1][3 + 1] :: -(-1) + 0 + 1 + 4 = 6
            for 1,2,3
            ...
        index_city_center: 1
            ...
    '''
    for index_city_center in range(number_city_centers):
        for index_line in range(index_city_center, number_city_centers):
            middle = int((index_city_center + index_line) / 2)
            for index_inner in range(index_city_center, index_line + 1):
                delta = list_city_centers[index_inner] - \
                    list_city_centers[middle]
                cost[index_city_center + 1][index_line + 1] += delta * (-1 if delta < 0 else 1)

    dp_table = [[float('inf') for _ in range(number_city_centers + 1)]
        for _ in range(number_shops + 1)]

    dp_table[0][0] = 0

    for index_shop in range(1, number_shops + 1):
        for index_city_center in range(1, number_city_centers + 1):
            for index_line in range(index_city_center):
                dp_table[index_shop][index_city_center] = \
                    min(dp_table[index_shop][index_city_center], 
                        dp_table[index_shop - 1][index_line] + cost[index_city_center - 1][index_line - 1])

    for item in dp_table:
        print(item)

    return dp_table[-1][-1]


print(knapsack(5, [1, 2, 3, 6, 7, 9, 11, 21, 40, 50]))