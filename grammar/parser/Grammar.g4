grammar Grammar;

@header{
    package parser;
}

prog    : include? function? main
        ;

main    : MAIN block
        ;

include : (INCLUDE VAR MEOL)+
        ;

stmt    : print MEOL
        | println MEOL
        | read MEOL
        | expr MEOL
        | ifelse
        | forloop
        | whileloop
        | var MEOL
        ;

print   : PRINT B_EXPR expr E_EXPR MEOL
        ;

println : PRINTLN B_EXPR expr E_EXPR MEOL WS
        ;

read    : SCAN B_EXPR expr E_EXPR MEOL
        ;

expr    : term SUM expr 
        | term MINUS expr
        ;

term    : fact MULT term 
        | fact DIV term 
        | fact PORC term
        ;

fact    : VAR 
        | NUM 
        | B_EXPR expr E_EXPR 
        ;

ifelse  : IF B_EXPR boolExpr E_EXPR block 
        | IF B_EXPR boolExpr E_EXPR block ELSE block
        ;

block   : 
        | B_BLOC (stmt)+ E_BLOC
        ;

boolExpr: fact 
        | NOT boolExpr 
        | fact relop fact 
        | TRUE 
        | FALSE
        ;

relop   : MAIOR 
        | MENOR 
        | EEQUALS 
        | Ma_IGUAL 
        | M_IGUAL 
        | DIF
        ;

forloop : FOR B_EXPR atr MMEOL boolExpr MMEOL atr E_EXPR B_BLOC block E_BLOC
        ;

whileloop   : WHILE B_EXPR boolExpr E_EXPR B_BLOC block E_BLOC
            ;

atr     : VAR EQUALS expr
        | VAR SUM SUM
        | VAR MINUS MINUS
        ;

var     : tipo VAR EQUALS expr
        ;

function    : tipo VAR B_EXPR ((tipo VAR)(VIG tipo VAR)*)? E_EXPR B_BLOC block E_BLOC
            ;

tipo    : INT
        | STRING
        | FLOAT
        | booleano
        ;

booleano : MYTRUE
        | MYFALSE
        ;

MAIN    : 'main';
INCLUDE : '#include';
PRINT   : 'print';
SCAN    : 'read';
INT     : 'int';
STRING  : 'string';
FLOAT   : 'float';
TRUE    : 'true';
FALSE   : 'false';
SUM     : '+';
DIV     : '/';
MULT    : '*';
MINUS   : '-';
PORC    : '%';
MYTRUE    : 'true';
MYFALSE   : 'false';
EQUALS  : '=';
B_EXPR  : '(';
E_EXPR  : ')';
B_BLOC  : '{';
E_BLOC  : '}';
SWITCH  : 'switch';
CASE    : 'case';
M_IGUAL : '<=';
Ma_IGUAL : '>=';
MAIOR    :'>';
MENOR    :'<';
EEQUALS  : '==';
DIF     : '!=';
PRINTLN : 'println';
WHILE   : 'while';
IF      : 'if';
ELSE    : 'else';
FOR     : 'for';
BREAK   : 'break';
DEFAULT : 'default';
MEOL     : ';';
NOT     : '!';
VAR     : [a-zA-Z][a-zA-Z0-9_]*;
VIG     : ',';
NUM     : [+-]?[0-9]+('.'[0-9]+)?;
WS      : [ \t\n] -> skip;
