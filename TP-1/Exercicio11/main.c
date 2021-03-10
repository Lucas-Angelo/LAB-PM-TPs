#include <stdio.h>
#include <string.h>

int isRecursivo(char str[]);
int isRecursivoPvd(char str[], int i);

void flush_in() {
    int ch;
    do {
        ch = fgetc(stdin);
    } while (ch != EOF && ch != '\n');
}

int main()
{
    char str[255] = { '\0' };
    int finaliza;

    do {
        //scanf(" %s%*[^\n]",&str);
        fgets(str, sizeof(str), stdin);
        str[strlen(str)-1]='\0';

        if(str[0]=='F' && str[1]=='I' && str[2]=='M')
            finaliza = 1;

        if(finaliza!=1) {
            if(isRecursivo(str)==1)
                printf("SIM\n");
            else
                printf("NAO\n");
        }

    } while (finaliza!=1);
    return 0;
}

int isRecursivo (char str[]) {
    return isRecursivoPvd(str, 0);
}

int isRecursivoPvd(char str[], int indice) {
    int igual;

    if (strlen(str) == indice)
        igual = 1;
    else if(str[indice]!=str[strlen(str)-indice-1])
        igual = 0;
    else
        igual = isRecursivoPvd (str, indice+1);

    return igual;
}