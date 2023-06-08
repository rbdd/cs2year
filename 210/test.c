#include <stdio.h>
int main(int argc, char *argv[]) {
    printf("argc = %d\n", argc);
    printf("[1] = %s\n", argv[0]);
    printf("[2] = %c%c%c\n", *(argv[2] + 2), *argv[3], *argv[2]);
}



