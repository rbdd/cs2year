import sys
import math

lines = sys.stdin.readlines()
l = []

for x in range(1, lines[0]): #O(n)
    ls = list(lines[x].split())
    l.append((ls[0], math.sqrt((ls[1])^2 + (ls[2])^2)))


def mergesort(a):
    n=len(a) #constant
    if n <= 1:
        return 1
    a1 = a[:n//2] #constant
    a2 = a[n//2:] #constant
    a1 = mergesort(a1) #T(n/2)
    a2 = mergesort(a2) #T(n/2)
    return merge(a1, a2) #S(n)
#total runnint time T(n) = c + 2 *T(n/2) + S(n)

def merge(a, b):
    m=len(a)
    n=len(a)
    i=0 #smallest element on list a
    j=0 #smallest element on list b
    output = list()
    while i < m and j<n: #still elements in both lists
        if a[i]<b[j]:
            output+=[a[i]]
            i+=1
        else:
            output+=[b[j]]
            j+=1
    output=output+a[i:]+b[j:]
    return output
