grammar grammarMiniC;

prog            : stmt+
                ;

/*
*   STMT TEM TUDO QUE DEVE TER NA LINGUAGEM
*   DESCRIÇÃO: https://github.com/wellingtondellamura/compilers/blob/master/final-work/description.txt
*/
stmt    : write EOL
        | writeln EOL
        | read EOL
        | expr EOL
        | if
        | ifelse
        | for
        | while
        | function
        | biblioteca
        ;

EOL     : ';';
WS      : [ \t\n] -> skip;