package org.example;

import org.example.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String line = "Dim user As User = New User(\"ABC\", #13/05/2003#, New Card(12234))";
        lexer.setLine(line);
        lexer.processLine();
        lexer.print();
    }
}