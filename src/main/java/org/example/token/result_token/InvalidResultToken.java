package org.example.token.result_token;

import org.example.token.InvalidToken;

public record InvalidResultToken(InvalidToken invalidToken, String sequence, int row, int column) {}
