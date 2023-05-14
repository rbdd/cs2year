import sys

def inputReader():
    iteration = int(sys.stdin.readline().strip())
    arcSet = list()
    for i in range(iteration):
        arcSet.append(sys.stdin.readline().strip().split())
    return arcSet, iteration    

def nodeRemover(arcSet,n):
    nodeToRemove = n-3
    arcRemoved = len(arcSet[nodeToRemove])
    newArcSet = []
    del arcSet[nodeToRemove]
    for i in range(0, len(arcSet)):
        s = []
        for x in arcSet[i]:
            if int(x) == nodeToRemove:
                arcRemoved+=1
            else:
                if int(x) > nodeToRemove:
                    s += [int(x)-1]
                else:
                    s += [int(x)]  
        newArcSet.append(s)
    return newArcSet, arcRemoved

arcSet, n = inputReader()
while n != 0:
    print(n-1)
    arcSet, arcRemoved = nodeRemover(arcSet, n)
    for set in arcSet:
        print(" ".join(str(x) for x in set))
    print(arcRemoved)
    arcSet, n = inputReader()
print(n)
    