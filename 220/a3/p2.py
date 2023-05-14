import sys

def inputReader():
    iteration = int(sys.stdin.readline().strip())
    arcSet = list()
    for i in range(iteration):
        arcSet.append(sys.stdin.readline().strip().split())
    return arcSet, len(arcSet)


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
        #print(n)
        for v in digraph[n]:
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
    #print(arcSet)
    for x in range(len(arcSet)):
        for i in arcSet[x]:
            if seen[int(i)] < seen[x] < done[x] < done[int(i)]:
                backArc += 1
            if seen[int(i)] < done[int(i)] < seen[x] < done[x]:
                crossArc += 1
    #print("seen")
    #print(seen)
    #print("done")
    #print(done)
    print(str(backArc) + " " + str(crossArc))
    arcSet, arcN = inputReader()

            






    