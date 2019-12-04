/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitor.Visitor;
import gramatica.GrammarLexer;
import gramatica.GrammarParser;
//import parser.GrammarLexer;
//import parser.GrammarParser;

public class Compiler {
    public static void main(String[] args) throws IOException {
        String input = "teste.c";
        CharStream charInput = new ANTLRFileStream(input);
        GrammarLexer lexer = new GrammarLexer(charInput);
        if (args.length >= 1) {
            input = args[0];
        }
        CharStream stream = null;
        if (input != null) {
            stream = new ANTLRFileStream(input);
        } else {
            stream = new ANTLRInputStream(System.in);
        }          
        //Lexer
        TokenStream tokens = new CommonTokenStream(lexer);  //nextToken 
        GrammarParser parser = new GrammarParser(tokens);         //Parser
        GrammarParser.ProgContext prog = parser.prog();        //Exec Parser prog
        showParseTreeFrame(prog,parser);
        Visitor pv = new Visitor();
        pv.visit(prog);
    }
    private static void showParseTreeFrame(ParseTree tree, GrammarParser parser) throws HeadlessException {
        JFrame frame = new JFrame("SRC: " + tree.getText());
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewr.setScale(1);
        panel.add(viewr);
        frame.add(panel);
        frame.setSize(1000, 600);
        frame.setState(JFrame.MAXIMIZED_HORIZ);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
