package entity;

public enum ComponentType {
    PARAGRAPH(" "),
    SENTENCE(" "),
    WORD(""),
    SYMBOL_NODE(""),
    PUNCTUATION_NODE(""),
    TEXT("\r\n");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
