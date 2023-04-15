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

