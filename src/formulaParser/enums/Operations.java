package formulaParser.enums;

public enum Operations {
    ADDITION("+"),
    EXTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    POWER("^");

    public final String operation;

    private Operations(String operation) {
        this.operation = operation;
    }
}
