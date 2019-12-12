#include biblio;
#include biblio2;

main {
    int abc = 1 + 1;
    println(abc);
    read(abc);
    for(int i=0; i<3; i++){
        abc = 1;
        if(abc > 0){
            print(abc);
        }
    }
    while(abc<10){
        abc++;
    }
}