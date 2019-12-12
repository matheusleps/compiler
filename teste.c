#include biblio;
#include biblio2;

main {
    double a;
    double b;
    double d;
    a = 10;
    a++;
    b = 10;
    d = a+b;
    printf(d);
    if(a>b){
        a = 30;
        printf(a);
    }
    while(a>b){
        b++;
        printf(b);
    }
}