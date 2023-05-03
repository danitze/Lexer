package org.example.token;

public class InvalidToken implements BaseToken {

    private final String description;

    public InvalidToken(String description) {
        this.description = description;
    }

    public InvalidToken() {
        this("Invalid token");
    }

    public String getDescription() {
        return description;
    }
}
