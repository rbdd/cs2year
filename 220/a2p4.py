import sys
import math

#functions_______________________________________
def mergesort(a):
    n=len(a) 
    if n <= 1:
        return a
    a1 = mergesort(a[:n//2])
    a2 = mergesort(a[n//2:])
    return merge(a1, a2)

def merge(a, b):
    ai = 0
    bi = 0 
    sorted = []
    while ai < len(a) and bi < len(b):
        if a[ai][1] < b[bi][1]:
            sorted.append(a[ai])
            ai+=1
        else:
            sorted.append(b[bi])
            bi+=1
    if a[ai:] == []:
        for bx in b[bi:]:
            sorted.append(bx)
    else:
        for ax in a[ai:]:
            sorted.append(ax)
    return sorted
#end functions_______________________________________
lines = sys.stdin.readlines()
l = []
for x in range(1, int(lines[0])+1):
    ls = list(lines[x].split())
    n = math.sqrt((int(ls[1]))**2 + (int(ls[2]))**2)
    l.append((ls[0], n))

sorted_names = mergesort(l)
for name in sorted_names:
    print(name[0])