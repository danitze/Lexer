package org.example.lexer;

import org.example.token.BaseToken;

public record TokenWithPosition(BaseToken token, int position) {
}
