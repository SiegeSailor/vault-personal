def euclidean(a, b):
    if a % b == 0:
        return b
    
    return euclidean(b, a % b)

print(euclidean(614, 513))