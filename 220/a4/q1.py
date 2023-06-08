import sys

def altDFSvisit(root):
    #print("current root is " + str(root))
    elements = []
    for node in range(len(pred)):
        #print("Dealing with node " + str(node))
        if color[node] == "W" and int(pred[node]) == root:
            color[node] = "G"
            if values[node] == "*" or values[node] == "+": 
                stack.append(values[node])
                rootStack.append(root)
                #print("Current Stack")
                #print(stack)
                altDFSvisit(node)
            else:
                stack.append(int(values[node]))
                #print("Current Stack")
                #print(stack)
    
    #print("current root is " + str(root))
    number = stack.pop()
    while type(number) == int:
        elements.append(number)
        number = stack.pop()
    if number == "+":
        stack.append(sum(elements))
    else:
        n = 1
        for x in elements:
            n *= x 
        stack.append(n)
    #print("Current Stack")
    #print(stack)
    return 
    

pred = sys.stdin.readline().strip().split(",")
values = sys.stdin.readline().strip().split(",")
while pred != ['']:
    stack = []
    rootStack = []
    ended = False
    color = ["W" for x in pred]
    root = pred.index("-1")
    stack.append(values[root])
    rootStack.append(root)
    color[root] = "G"
    while not ended:
        altDFSvisit(root)
        if type(stack[0]) == int:
            ended = True
    print(stack.pop())
    
    pred = sys.stdin.readline().strip().split(",")
    values = sys.stdin.readline().strip().split(",")

