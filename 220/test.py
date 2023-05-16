counter = 2
result = (7**(counter)) % 13
while result != 2:
    counter+=1
    result = (7**(counter)) % 13
    print(counter)

