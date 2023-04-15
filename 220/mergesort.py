import merge
def mergesort(a):
    n=len(a) #constant
    if n <= 1:
        return 1
    a1 = a[:n//2] #constant
    a2 = a[n//2:] #constant
    a1 = mergesort(a1) #T(n/2)
    a2 = mergesort(a2) #T(n/2)
    return merge.merge(a1, a2) #S(n)
#total runnint time T(n) = c + 2 *T(n/2) + S(n)