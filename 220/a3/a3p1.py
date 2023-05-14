import sys

def inputReader():
    iteration = int(sys.stdin.readline().strip())
    arcSet = list()
    for i in range(iteration):
        levelNodes = sys.stdin.readline().strip().split()
        for node in levelNodes:
            arcSet.append((i, int(node)))
    return arcSet, iteration    

def nodeRemover(arcSet,n):
    nodeToRemove = n-3
    arcRemoved = 0
    newArcSet = []
    for i in range(0, len(arcSet)):
        if arcSet[i][0] != nodeToRemove and arcSet[i][1] != nodeToRemove:
            newArcSet.append(arcSet[i])      
        else:
            arcRemoved+=1
    for i in range(len(newArcSet)):
        if newArcSet[i][0] > nodeToRemove:
            newArcSet[i] = (newArcSet[i][0]-1, newArcSet[i][1])
        if newArcSet[i][1] > nodeToRemove:
            newArcSet[i] = (newArcSet[i][0], newArcSet[i][1]-1)
    return newArcSet, arcRemoved

arcSet, n = inputReader()
while n != 0:
    print(n-1)
    arcSet, arcRemoved = nodeRemover(arcSet, n)
    for i in range(n-1):
        s = ""
        for node in arcSet:
            if node[0] == i:
                s += str(node[1]) + " "
        print(s.strip())
    print(arcRemoved)
    arcSet, n = inputReader()
print(n)
    


    