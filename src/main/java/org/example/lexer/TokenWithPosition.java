package org.example.lexer;

import org.example.token.Token;

public record TokenWithPosition(Token token, int position) {
}
