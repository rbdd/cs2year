 #include <stdio.h>

int inner_product(int a[3], int b[3])
{
    return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]; 
}

void printarr(int arr[3], int size){
    for (int i = 0; i <= size; i++)
    {
        if (i == 0)
        {
            printf("<%i, ", arr[i]);
        } else if (i == size)
        {
            printf("%i>", arr[i]);
        } else
        {
            printf("%i, ", arr[i]);
        }
    }
}

int main()
{
    int vectorA[3], vectorB[3];
    int result;
    scanf("%d %d %d", &vectorA[0], &vectorA[1], &vectorA[2]);
    scanf("%d %d %d", &vectorB[0], &vectorB[1], &vectorB[2]);
    result = inner_product(vectorA, vectorB);
    printf("The inner product of ");
    printarr(vectorA, 2);
    printf(" and ");
    printarr(vectorB, 2);
    printf(" is %d.", result);
}