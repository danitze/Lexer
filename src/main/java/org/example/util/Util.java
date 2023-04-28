package org.example.util;

import org.example.token.Token;

import java.util.List;

public interface Util {

    List<Token> keyWordTokens = List.of(
            Token.ADD_HANDLER,
            Token.ADDRESS_OF,
            Token.ALIAS,
            Token.AND,
            Token.AND_ALSO,
            Token.AS,
            Token.ASSEMBLY,
            Token.AUTO,
            Token.BOOLEAN,
            Token.BY_REF,
            Token.BYTE,
            Token.BY_VAL,
            Token.CALL,
            Token.CASE,
            Token.CATCH,
            Token.C_BOOL,
            Token.C_BYTE,
            Token.C_CHAR,
            Token.C_DATE,
            Token.C_DBL,
            Token.C_DEC,
            Token.CHAR,
            Token.C_INT,
            Token.CLASS,
            Token.C_LNG,
            Token.C_OBJ,
            Token.CONST,
            Token.CONTINUE,
            Token.C_S_BYTE,
            Token.C_SHORT,
            Token.C_SNG,
            Token.C_STR,
            Token.C_TYPE,
            Token.C_TYPE_DYNAMIC,
            Token.DATE,
            Token.DECIMAL,
            Token.DECLARE,
            Token.DEFAULT,
            Token.DELEGATE,
            Token.DIM,
            Token.DIRECT_CAST,
            Token.DO,
            Token.DOUBLE,
            Token.EACH,
            Token.ELSE,
            Token.ELSE_IF,
            Token.END,
            Token.ENUM,
            Token.ERASE,
            Token.ERROR,
            Token.EVENT,
            Token.EXIT,
            Token.FALSE,
            Token.FINALLY,
            Token.FOR,
            Token.FRIEND,
            Token.FUNCTION,
            Token.GET,
            Token.GET_TYPE,
            Token.GO_SUB,
            Token.GO_TO,
            Token.HANDLES,
            Token.IF,
            Token.IMPLEMENTS,
            Token.IMPORTS,
            Token.IN,
            Token.INHERITS,
            Token.INTEGER,
            Token.INTERFACE,
            Token.IS,
            Token.IS_NOT,
            Token.LET,
            Token.LIB,
            Token.LIKE,
            Token.LONG,
            Token.LOOP,
            Token.ME,
            Token.MOD,
            Token.MODULE,
            Token.MUST_INHERIT,
            Token.MUST_OVERRIDE,
            Token.MY_BASE,
            Token.MY_CLASS,
            Token.NAMESPACE,
            Token.NEW,
            Token.NEXT,
            Token.NOT,
            Token.NOTHING,
            Token.NOT_INHERITABLE,
            Token.NOT_OVERRIDABLE,
            Token.OBJECT,
            Token.OF,
            Token.ON,
            Token.OPERATOR,
            Token.OPTION,
            Token.OPTIONAL,
            Token.OR,
            Token.OR_ELSE,
            Token.OUT,
            Token.OVERLOADS,
            Token.OVERRIDABLE,
            Token.OVERRIDES,
            Token.PARAM_ARRAY,
            Token.PRESERVE,
            Token.PRIVATE,
            Token.PROPERTY,
            Token.PROTECTED,
            Token.PUBLIC,
            Token.RAISE_EVENT,
            Token.READ_ONLY,
            Token.RE_DIM,
            Token.REM,
            Token.REMOVE_HANDLER,
            Token.RESUME,
            Token.RETURN,
            Token.S_BYTE,
            Token.SELECT,
            Token.SET,
            Token.SHADOWS,
            Token.SHARED,
            Token.SHORT,
            Token.SINGLE,
            Token.STATIC,
            Token.STEP,
            Token.STOP,
            Token.STRING,
            Token.STRUCTURE,
            Token.SUB,
            Token.SYNC_LOCK,
            Token.THEN,
            Token.THROW,
            Token.TO,
            Token.TRUE,
            Token.TRY,
            Token.TYPE_OF,
            Token.UNICODE,
            Token.UNTIL,
            Token.U_SHORT,
            Token.USING,
            Token.VARIANT,
            Token.WEND,
            Token.WHEN,
            Token.WHILE,
            Token.WIDENING,
            Token.WITH,
            Token.WITH_EVENTS,
            Token.WRITE_ONLY,
            Token.XOR,
            Token.YIELD
    );

    List<Token> operatorTokens = List.of(
            Token.ADDITION,
            Token.SUBTRACTION,
            Token.MULTIPLICATION,
            Token.DIVISION,
            Token.EXPONENTIATION,
            Token.INTEGER_DIVISION,
            Token.CONCATENATION,
            Token.EQUALS,
            Token.NOT_EQUALS,
            Token.LESS,
            Token.LESS_OR_EQUAL,
            Token.GREATER,
            Token.GREATER_OR_EQUAL,
            Token.ADDITION_ASSIGN,
            Token.SUBTRACTION_ASSIGN,
            Token.MULTIPLICATION_ASSIGN,
            Token.DIVISION_ASSIGN,
            Token.INTEGER_DIVISION_ASSIGN,
            Token.EXPONENTIATION_ASSIGN,
            Token.CONCATENATION_ASSIGN
    );

    List<Token> punctuationTokens = List.of(
            Token.PARENTHESIS_OPEN,
            Token.PARENTHESIS_CLOSE,
            Token.BRACKET_OPEN,
            Token.BRACKET_CLOSE,
            Token.CURLY_BRACE_OPEN,
            Token.CURLY_BRACE_CLOSE,
            Token.PERIOD,
            Token.COMMA,
            Token.COLON,
            Token.SEMICOLON,
            Token.QUESTION_MARK,
            Token.POUND_SIGN
    );

    List<Token> whitespaceTokens = List.of(
            Token.SPACE,
            Token.TAB
    );

    static boolean isSpace(char symbol) {
        return symbol == ' ';
    }

    static boolean isTab(char symbol) {
        return symbol == '\t';
    }

    static boolean isWhiteSpace(char symbol) {
        return isSpace(symbol) || isTab(symbol);
    }

    static boolean isDigit(char symbol) {
        return Character.isDigit(symbol);
    }

    static boolean isIdentifierBegin(char symbol) {
        return (symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z') || symbol == '_';
    }

    static boolean isPoundSign(char symbol) {
        return symbol == '#';
    }

    static boolean isDot(char symbol) {
        return symbol == '.';
    }

    static boolean isCommentStart(char symbol) {
        return symbol == '\'';
    }

    static boolean isOperator(char symbol) {
        return "+-*/^\\&=<>".contains(String.valueOf(symbol));
    }

    static boolean isPunctuation(char symbol) {
        return "()[]{}.,:;?#".contains(String.valueOf(symbol));
    }

    static boolean isDoubleQuotes(char symbol) {
        return symbol == '\"';
    }

    static boolean isTokenEnd(char symbol) {
        return isWhiteSpace(symbol) || isOperator(symbol) || isPunctuation(symbol);
    }
}
