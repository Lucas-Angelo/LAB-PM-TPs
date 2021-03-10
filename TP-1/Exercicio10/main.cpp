#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isPalindromo(char texto[]);

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
            if(isPalindromo(str)==1)
                printf("SIM\n");
            else
                printf("NAO\n");
        }

    } while (finaliza!=1);
    return 0;
}

int isPalindromo(char texto[]){
    int igual = 1;
    char invertida[255] = "";

    for(int i=0;i<strlen(texto);i++) {
        invertida[i] = texto[strlen(texto)-i-1];
    }

    for(int i=0;i<strlen(texto);i++) {
        if(texto[i]!=invertida[i])
            igual = 0;
    }

    return igual;
}