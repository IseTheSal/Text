package by.learning.project.composite.parser;

public abstract class AbstractParser implements TextParser {
    protected TextParser next;

    protected AbstractParser(TextParser next) {
        this.next = next;
    }

    public void setNext(TextParser parser) {
        this.next = parser;
    }
}
