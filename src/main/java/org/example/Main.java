package org.example;

import org.example.lexer.Lexer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        lexer.processFile("src/main/resources/HelloWorld.vb");
        lexer.print();
    }
}