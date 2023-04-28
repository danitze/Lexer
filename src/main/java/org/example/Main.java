package org.example;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String line = "Dim a As Integer = 134A";
        lexer.processLine(line);
        System.out.println(lexer.getTokens().toString());
    }
}