import sys
import queue

def inputReader():
    iteration = int(sys.stdin.readline().strip())
    arcSet = list()
    for i in range(iteration):
        arcSet.append(sys.stdin.readline().strip().split())
    return arcSet, len(arcSet)

def BFS(digraph, arcN):
    colour = list()
    pred = list()
    d = list()
    global Queue
    Queue = list()

    def BFSVisit(n):
        global Queue
        colour[n] = "G"
        d[n] = 0
        Queue.append(n)
        while not Queue == []:
            u = Queue[0]
            for v in digraph[u]:
                if colour[int(v)] == "W":
                    colour[int(v)] = "G"
                    pred[int(v)] = u
                    d[int(v)] = d[u] + 1
                    Queue.append(int(v))
            Queue = Queue[1:]
            colour[u] = "B"

    for n in range(arcN):
        colour.append("W")
        pred.append(None)
        d.append(0)

    if colour[1] ==  "W":
        BFSVisit(1)

    return d, pred

arcSet, arcN = inputReader()
while arcN != 0:
    d, pred = BFS(arcSet, arcN)
    m = max(d)
    if pred == [None for x in range(len(pred))]:
        mnode = 1
    else:
        for n in range(len(d)):
            if d[n] == m:
                mnode = n
    print(str(m) + " " + str(mnode))
    arcSet, arcN = inputReader()

            






    