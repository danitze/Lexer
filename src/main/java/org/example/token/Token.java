package org.example.token;

public enum Token implements BaseToken {
    IDENTIFIER("IDENTIFIER"),
    INTEGER_IDENTIFIER("INTEGER_IDENTIFIER"),
    LONG_IDENTIFIER("LONG_IDENTIFIER"),
    FLOAT_IDENTIFIER("FLOAT_IDENTIFIER"),
    STRING_IDENTIFIER("STRING_IDENTIFIER"),
    COMMENTARY("Commentary"),
    LINE_CONTINUATION(" _"),

    //Keywords
    ADD_HANDLER("AddHandler"),
    ADDRESS_OF("AddressOf"),
    ALIAS("Alias"),
    AND("And"),
    AND_ALSO("AndAlso"),
    AS("As"),
    ASSEMBLY("Assembly"),
    AUTO("Auto"),
    BOOLEAN("Boolean"),
    BY_REF("ByRef"),
    BYTE("Byte"),
    BY_VAL("ByVal"),
    CALL("Call"),
    CASE("Case"),
    CATCH("Catch"),
    C_BOOL("CBool"),
    C_BYTE("CByte"),
    C_CHAR("CChar"),
    C_DATE("CDate"),
    C_DBL("CDbl"),
    C_DEC("CDec"),
    CHAR("Char"),
    C_INT("CInt"),
    CLASS("Class"),
    C_LNG("CLng"),
    C_OBJ("CObj"),
    CONST("Const"),
    CONTINUE("Continue"),
    C_S_BYTE("CSByte"),
    C_SHORT("CShort"),
    C_SNG("CSng"),
    C_STR("CStr"),
    C_TYPE("CType"),
    C_TYPE_DYNAMIC("CTypeDynamic"),
    DATE("Date"),
    DECIMAL("Decimal"),
    DECLARE("Declare"),
    DEFAULT("Default"),
    DELEGATE("Delegate"),
    DIM("Dim"),
    DIRECT_CAST("DirectCast"),
    DO("Do"),
    DOUBLE("Double"),
    EACH("Each"),
    ELSE("Else"),
    ELSE_IF("ElseIf"),
    END("End"),
    ENUM("Enum"),
    ERASE("Erase"),
    ERROR("Error"),
    EVENT("Event"),
    EXIT("Exit"),
    FALSE("False"),
    FINALLY("Finally"),
    FOR("For"),
    FRIEND("Friend"),
    FUNCTION("Function"),
    GET("Get"),
    GET_TYPE("GetType"),
    GO_SUB("GoSub"),
    GO_TO("GoTo"),
    HANDLES("Handles"),
    IF("If"),
    IMPLEMENTS("Implements"),
    IMPORTS("Imports"),
    IN("In"),
    INHERITS("Inherits"),
    INTEGER("Integer"),
    INTERFACE("Interface"),
    IS("Is"),
    IS_NOT("IsNot"),
    LET("Let"),
    LIB("Lib"),
    LIKE("Like"),
    LONG("Long"),
    LOOP("Loop"),
    ME("Me"),
    MOD("Mod"),
    MODULE("Module"),
    MUST_INHERIT("MustInherit"),
    MUST_OVERRIDE("MustOverride"),
    MY_BASE("MyBase"),
    MY_CLASS("MyClass"),
    NAMESPACE("Namespace"),
    NEW("New"),
    NEXT("Next"),
    NOT("Not"),
    NOTHING("Nothing"),
    NOT_INHERITABLE("NotInheritable"),
    NOT_OVERRIDABLE("NotOverridable"),
    OBJECT("Object"),
    OF("Of"),
    ON("On"),
    OPERATOR("Operator"),
    OPTION("Option"),
    OPTIONAL("Optional"),
    OR("Or"),
    OR_ELSE("OrElse"),
    OUT("Out"),
    OVERLOADS("Overloads"),
    OVERRIDABLE("Overridable"),
    OVERRIDES("Overrides"),
    PARAM_ARRAY("ParamArray"),
    PRESERVE("Preserve"),
    PRIVATE("Private"),
    PROPERTY("Property"),
    PROTECTED("Protected"),
    PUBLIC("Public"),
    RAISE_EVENT("RaiseEvent"),
    READ_ONLY("ReadOnly"),
    RE_DIM("ReDim"),
    REM("REM"),
    REMOVE_HANDLER("RemoveHandler"),
    RESUME("Resume"),
    RETURN("Return"),
    S_BYTE("SByte"),
    SELECT("Select"),
    SET("Set"),
    SHADOWS("Shadows"),
    SHARED("Shared"),
    SHORT("Short"),
    SINGLE("Single"),
    STATIC("Static"),
    STEP("Step"),
    STOP("Stop"),
    STRING("String"),
    STRUCTURE("Structure"),
    SUB("Sub"),
    SYNC_LOCK("SyncLock"),
    THEN("Then"),
    THROW("Throw"),
    TO("To"),
    TRUE("True"),
    TRY("Try"),
    TYPE_OF("TypeOf"),
    UNICODE("Unicode"),
    UNTIL("Until"),
    U_SHORT("UShort"),
    USING("Using"),
    VARIANT("Variant"),
    WEND("Wend"),
    WHEN("When"),
    WHILE("While"),
    WIDENING("Widening"),
    WITH("With"),
    WITH_EVENTS("WithEvents"),
    WRITE_ONLY("WriteOnly"),
    XOR("Xor"),
    YIELD("Yield"),

    //Values
    INT_VALUE("Int value"),
    FLOAT_VALUE("FLOAT value"),
    CHAR_VALUE("Char value"),
    STRING_VALUE("String value"),
    DATE_VALUE("Date value"),

    //Operators
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    EXPONENTIATION("^"),
    INTEGER_DIVISION("\\"),
    CONCATENATION("&"),
    EQUALS("="),
    NOT_EQUALS("<>"),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    ADDITION_ASSIGN("+="),
    SUBTRACTION_ASSIGN("-="),
    MULTIPLICATION_ASSIGN("*="),
    DIVISION_ASSIGN("/="),
    INTEGER_DIVISION_ASSIGN("\\="),
    EXPONENTIATION_ASSIGN("^="),
    CONCATENATION_ASSIGN("&="),

    //Punctuation
    PARENTHESIS_OPEN("("),
    PARENTHESIS_CLOSE(")"),
    BRACKET_OPEN("["),
    BRACKET_CLOSE("]"),
    CURLY_BRACE_OPEN("{"),
    CURLY_BRACE_CLOSE("}"),
    PERIOD("."),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),
    QUESTION_MARK("?"),
    POUND_SIGN("#"),

    //Whitespace
    SPACE(" "),
    TAB("\t")
    ;

    private final String value;

    Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
