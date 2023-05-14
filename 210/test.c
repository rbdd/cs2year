#include <stdio.h>
#include <ctype.h>
#include <string.h>

int process_file(char* filename, char words[20][15]){
    FILE *file;
    int counter = 0;
    file = fopen(filename, "r");
    while (fscanf (file, "%s", words[counter]) != EOF){
        counter++;
    }
    fclose(file);
    return counter;
}

int main(){
    char filename[15] = "Words1.txt";
    char words[20][15];
    int count = process_file(filename, words);
    for (int i = 0; i < count; i++) {
        printf("%s\n", words[i]);
    }
    return 0;
}



