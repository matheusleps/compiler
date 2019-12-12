
package visitor;

import gramatica.GrammarBaseVisitor;
import gramatica.GrammarParser;

public class Visitor extends GrammarBaseVisitor<Object>{
    	@Override 
   public Object visitExprSum(GrammarParser.ExprSumContext ctx){
        Double a = (Double) visit(ctx.term());
        Double b = (Double) visit(ctx.expr());
        return a + b;
    }
    @Override
    public Object visitExprMinus(GrammarParser.ExprMinusContext ctx){
        Double a = (Double) visit(ctx.term());
        Double b = (Double) visit(ctx.expr());
        return a - b;
    }
    @Override
    public Object visitExprTerm(GrammarParser.ExprTermContext ctx){
        return visit(ctx.term());
    }
    @Override
    public Object visitTermMult(GrammarParser.TermMultContext ctx){
        Double a = (Double) visit(ctx.fact());
        Double b = (Double) visit(ctx.expr());
        return a * b;
    }
    @Override
    public Object visitTermDiv(GrammarParser.TermDivContext ctx){
        Double a = (Double) visit(ctx.fact());
        Double b = (Double) visit(ctx.expr());
        return a / b;
    }
    @Override
    public Object visitTermPorc(GrammarParser.TermPorcContext ctx){
        Double a = (Double) visit(ctx.fact());
        Double b = (Double) visit(ctx.expr());
        return a % b;
    }
    @Override
    public Object visitTermFact(GrammarParser.TermFactContext ctx){
        return visit(ctx.fact());
    }
    @Override
    public Object visitFactExpr(GrammarParser.FactExprContext ctx){
        return visit(ctx.expr());
    }
    /*@Override
    public Object visitFactVar(GrammarParser.FactVarContext ctx){
        return visit(ctx.expr());
    }*/
    @Override 
    public Object visitPrintlnR(GrammarParser.PrintlnRContext ctx) { 
        Object obj = visit(ctx.expr());
        System.out.println(obj);
        return obj; 
    }
    @Override 
    public Object visitPrintR(GrammarParser.PrintRContext ctx) { 
        Object obj = visit(ctx.expr());
        System.out.print(obj);
        return obj; 
    }

}
