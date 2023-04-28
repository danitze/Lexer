package org.example;

import org.example.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String line = "Dim a As String = \"abc";
        lexer.setLine(line);
        lexer.processLine();
        lexer.print();
    }
}