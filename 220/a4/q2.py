import sys

def DistanceBetweenBoulders(b1, b2):
    if ((b1[0] - b2[0])**2 + (b1[1] - b2[1])**2)**(1/2) > 100:
        return 10000000000 + ((b1[0] - b2[0])**2 + (b1[1] - b2[1])**2)**(1/2)
    return ((b1[0] - b2[0])**2 + (b1[1] - b2[1])**2)**(1/2) 

input = sys.stdin.readline().strip().split(',')
while not input == ['']:
    n = int(input[0])
    boulders = []
    for n in range(1, len(input), 2):
        boulders.append((float(input[n]), float(input[n+1])))
    distances = [DistanceBetweenBoulders(boulders[0], boulders[x]) for x in range(len(boulders))]
    colour = ["W" for x in range(len(boulders))]
    colour[0] = "B"
    while not colour == ["B" for x in range(len(boulders))]:
        u = distances.index(min([distances[i] for i in range(len(colour)) if colour[i] == "W"]))
        colour[u] = "B"
        #print(colour)
        for x in range(len(boulders)):
            if colour[x] == "W":
                distances[x] = min(distances[x], distances[u] + DistanceBetweenBoulders(boulders[u], boulders[x]))
    if distances[-1] > 10000000000:
        print(-1)
    else:
        print("{:.2f}".format(distances[-1]))
    input = sys.stdin.readline().strip().split(',')
    
