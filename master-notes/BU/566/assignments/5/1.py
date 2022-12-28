MILLION = 1000000

def knapsack(budget_limit, list_houses):
    matrix = [[0 for _ in range(budget_limit + 1)] 
        for _ in range(len(list_houses) + 1)]

    list_houses_copied = list_houses.copy()
    list_houses_copied.insert(0, [0, 0])

    for index_house in range(len(list_houses_copied)):
        for budget in range(budget_limit + 1):
            if budget == 0 or index_house == 0:
                continue

            house_cost = list_houses_copied[index_house][0]
            house_percentage = list_houses_copied[index_house][1]
            house_profit = int(house_cost * MILLION * house_percentage)

            remain = budget - house_cost
            current_profit = house_profit if remain >= 0 else 0
            last_profit = matrix[index_house - 1][max(remain, 0)]

            matrix[index_house][budget] = \
                max(
                    matrix[index_house - 1][budget],
                    current_profit + last_profit
                )

    for item in matrix:
        print(item)
            

knapsack(10, [[3, 0.02], [2, 0.03], [4, 0.03], [3, 0.02]])