grammar Grammar;

@header{
    package gramatica;
}

prog    : include? function? main
        ;

main    : MAIN block
        ;

include : (INCLUDE VAR MEOL)+
        ;

stmt    : print MEOL                        #stmtPrint
        | println MEOL                      #stmtPrintln
        | read MEOL                         #stmtRead     
        | expr MEOL                         #stmtExpr
        | ifelse                            #stmtifelse
        | forloop                           #stmtForloop
        | whileloop                         #stmtWhileloop
        | var MEOL                          #stmtVar
        | atr MEOL                          #stmtAtr
        ;

print   : PRINT B_EXPR expr E_EXPR          #printR
        ;

println : PRINTLN B_EXPR expr E_EXPR        #printlnR
        ;

read    : SCAN B_EXPR expr E_EXPR           #readR
        ;

expr    : term SUM expr                     #exprSum
        | term MINUS expr                   #exprMinus
        | term                              #exprTerm
        ;

term    : fact MULT term                    #termMult
        | fact DIV term                     #termDiv
        | fact PORC term                    #termPorc
        | fact                              #termFact
        ;

fact    : VAR                               #factVar
        | NUM                               #factNum
        | B_EXPR expr E_EXPR                #factExpr
        ;

ifelse  : MIF B_EXPR boolExpr E_EXPR block              #ifif
        | MIF B_EXPR boolExpr E_EXPR block ELSE block   #ififelse
        ;

block   : stmt?                             #blocStmt
        | B_BLOC stmt* E_BLOC               #blockStmtP
        ;

boolExpr: expr                              #boolExprR
        | NOT boolExpr                      #notBool
        | expr relop expr                   #boolRelop
        | booleano                          #boolbool
        ;

relop   : MAIOR
        | MENOR 
        | EEQUALS 
        | Ma_IGUAL 
        | M_IGUAL 
        | DIF
        ;

forloop : FOR B_EXPR var MEOL boolExpr MEOL atr E_EXPR block    #forloopR
        ;

whileloop   : WHILE B_EXPR boolExpr E_EXPR B_BLOC block E_BLOC  #whileloopR
            ;

atr     : VAR EQUALS expr                   #atrEqual
        | VAR SUM SUM                       #atrPlusPlus
        | VAR MINUS MINUS                   #atrMinusMinus
        ;

var     : tipo VAR EQUALS expr              #varAtr
        ;

function    : tipo VAR B_EXPR ((tipo VAR)(VIG tipo VAR)*)? E_EXPR B_BLOC block E_BLOC   #functionR
            ;

tipo    : INT                               #tipoInt
        | STRING                            #tipoString
        | FLOAT                             #tipoFloat
        | booleano                          #tipoBool
        ;

booleano : MYTRUE                           #boolTrue
        | MYFALSE                           #boolFalse
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
MIF      : 'if';
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
