#include <stdio.h>
#define STOP 0

/* Function: main 
*/

int main(){
    int counter;
    int startPoint;
    printf("Ener a positive number");
    scanf("%d",&startPoint);
    for (counter=startPoint; counter>=STOP; counter--)
        printf("%d\n", counter);
}