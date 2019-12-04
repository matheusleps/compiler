
package visitor;

import gramatica.GrammarBaseVisitor;
import gramatica.GrammarParser;

public class Visitor extends GrammarBaseVisitor<Object>{
    	@Override 
        public Object visitPrintlnR(GrammarParser.PrintlnRContext ctx) { 
            Object obj = visit(ctx.expr());
            System.out.println(obj);
            return obj; 
        }

}
