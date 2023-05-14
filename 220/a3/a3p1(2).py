import sys


def inputReader():
    iteration = int(sys.stdin.readline().strip())
    arcN = 0
    arcSet = list()
    for i in range(iteration):
        arcSet.append(sys.stdin.readline().strip().split())
        arcN += len(arcSet[i])
    print(arcN)
    return arcSet, arcN


def DFS(digraph, arcN):
    colour = list()
    pred = list()
    seen = list()
    done = list()
    global time
    time = 0

    def DFSrecursiveVisit(n):
        global time
        colour[n] = "G"
        seen[n] = time
        time += 1
        for v in digraph[n]:
            print(n)
            if colour[int(v)] == "W":
                pred[int(v)] = n
                DFSrecursiveVisit(int(v))
        colour[n] = "B"
        done[n] = time
        time += 1

    for n in range(arcN):
        colour.append("W")
        pred.append(None)
        seen.append(0)
        done.append(0)

    for n in range(arcN):
        if colour[n] ==  "W":
            DFSrecursiveVisit(n)

    return pred, seen, done

arcSet, arcN = inputReader()
while arcN != 0:
    backArc = 0
    crossArc = 0
    pred, seen, done = DFS(arcSet, arcN)
    for x in range(len(arcSet)):
        for i in range(len(arcSet[x])):
            if seen[i] < seen[x] < done[x] < done[i]:
                backArc += 1
            if seen[i] < done[i] < seen[x] < done[x]:
                crossArc += 1
    print(backArc + " " + crossArc)
    arcSet, arcN = inputReader()

            






    