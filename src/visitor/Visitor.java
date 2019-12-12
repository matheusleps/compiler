
package visitor;

import gramatica.GrammarBaseVisitor;
import gramatica.GrammarLexer;
import gramatica.GrammarParser;
import java.util.Scanner;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Visitor extends GrammarBaseVisitor<Object>{
    @Override
    public Object visitIfStmtX(GrammarParser.IfStmtXContext ctx) {
        Boolean condicao = (Boolean) visit(ctx.cond());
        if (condicao) {
            return visit(ctx.b1);
        }
        return null;
    }
    
    @Override 
    public Object visitWhilex(GrammarParser.WhilexContext ctx) {
        Boolean cond = (Boolean) visit(ctx.cond());
        while(cond){
            cond = (Boolean) visit(ctx.cond());
            visit (ctx.block());
        }
        return 0d;
    }
    
    @Override
    public Object visitIfStmtElse(GrammarParser.IfStmtElseContext ctx) {
        Boolean condicao = (Boolean) visit(ctx.cond());
        
        if (condicao) {
            return visit(ctx.b1);
        } else {
            return visit(ctx.b2);
        }
    }
    @Override
    public Object visitCondExpr(GrammarParser.CondExprContext ctx) {
        Double d = (Double) visit(ctx.expr());
        return d > 0;
    }
    
    @Override
    public Object visitCondRelop(GrammarParser.CondRelopContext ctx) {
        Double a = (Double) visit(ctx.expr(0));
        Double b = (Double) visit(ctx.expr(1));
        
        int op = ctx.relop.getType();
        switch (op) {
            case GrammarLexer.EEQUALS:
                return a.equals(b);
            case GrammarLexer.MENOR:
                return a < b;
            case GrammarLexer.MAIOR:
                return a > b;
            case GrammarLexer.M_IGUAL:
                return a <= b;
            case GrammarLexer.Ma_IGUAL:
                return a >= b;
        }
        return null;
    }
    
    @Override
    public Object visitExprPlus(GrammarParser.ExprPlusContext ctx) {
        Double a = (Double) visit(ctx.term());
        Double b = (Double) visit(ctx.expr());
        return a + b;
    }

    @Override
    public Object visitExprMin(GrammarParser.ExprMinContext ctx) {
        Double a = (Double) visit(ctx.term());
        Double b = (Double) visit(ctx.expr());
        return a - b;
    }
    @Override
    public Object visitExprTerm(GrammarParser.ExprTermContext ctx) {
        return visit(ctx.term());
    }
    @Override
    public Object visitTermMult(GrammarParser.TermMultContext ctx) {
        Double a = (Double) visit(ctx.fact());
        Double b = (Double) visit(ctx.expr());
        return a * b;
    }

    @Override
    public Object visitTermDiv(GrammarParser.TermDivContext ctx) {
        Double a = (Double) visit(ctx.fact());
        Double b = (Double) visit(ctx.expr());
        return a / b;
    }
    @Override
    public Object visitTermFact(GrammarParser.TermFactContext ctx) {
        return visit(ctx.fact());
    }
    @Override
    public Object visitExpr2par(GrammarParser.Expr2parContext ctx) {
        return visit(ctx.expr());
    }
    @Override
    public Object visitExprNum(GrammarParser.ExprNumContext ctx) {
        return Double.parseDouble(ctx.NUM().getText());
    }
    @Override
    public Object visitExprId(GrammarParser.ExprIdContext ctx) {
        //System.out.println(Variaveis.getInstance().getSymbol(ctx.VAR().getText())[1]);
        return Variaveis.getInstance().getSymbol(ctx.VAR().getText())[1];
    }
    
    @Override
    public Object visitAttrPlusPlus(GrammarParser.AttrPlusPlusContext ctx){
        Object[] var = Variaveis.getInstance().getSymbol(ctx.VAR().getText());
        var[1] = (double) var[1] + 1;
        Variaveis.getInstance().addSymbol(ctx.VAR().getText(), var);
        return 0;
    }
    
    @Override 
    public Object visitAttrExpr(GrammarParser.AttrExprContext ctx) {      
        if(Variaveis.getInstance().getSymbol(ctx.VAR().getText())!= null){

            Object aux= visit(ctx.expr());
            if(aux == null){
                aux = ctx.expr().getText();
            } 
            Object[] var = Variaveis.getInstance().getSymbol(ctx.VAR().getText());
            if(var[0].equals("int")){ 
                System.out.println("int");
                var[1] = (int)Double.parseDouble(aux.toString());
            }
            else if(var[0].equals("double")){           
                var[1] = Double.parseDouble(aux.toString());
            }
            else if(var[0].equals("boolean")){
                if(aux.toString().equals("true") || aux.toString().equals("false")){
                    var[1] = aux;
                }else{
                    throw new ParseCancellationException("Esperava um booleano");
                }
            }else if(var[0].equals("string")){
                if(aux.toString().startsWith("\"")){
                    var[1] = aux;
                }else{
                    throw new ParseCancellationException("Esperava uma string");
                }
            }
        }else{
            throw new ParseCancellationException("Variavel inexistente");
        }         
    return 0;
    }
    @Override
    public Object visitDeclSimple(GrammarParser.DeclSimpleContext ctx) {
        Object[] var = new Object[2];
        var[0] = ctx.type().getText();
        Variaveis.getInstance().addSymbol(ctx.VAR().getText(), var);
        return 0d;
    }
    
    @Override
    public Object visitPrint(GrammarParser.PrintContext ctx){
        Object o = visit(ctx.expr());
        System.out.println(o);
        return o;
    }
    
    @Override
    public Object visitAttrScan(GrammarParser.AttrScanContext ctx) {
        Scanner s = new Scanner(System.in);
        Object[] value = new Object[2];
        value[1] = s.nextLine();
        Variaveis.getInstance().addSymbol(ctx.VAR().getText(), value);
        return value;
    }
}
