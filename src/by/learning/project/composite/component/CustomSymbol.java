package by.learning.project.composite.component;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomSymbol implements TextComponent {
    private static final Logger logger = LogManager.getLogger(CustomSymbol.class);
    private final char symbol;
    private final ComponentType componentType = ComponentType.SYMBOL;

    public CustomSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(TextComponent component) throws IllegalAccessException {
        logger.log(Level.WARN, "add() is not available");
        throw new IllegalAccessException();
    }

    @Override
    public TextComponent getByIndex(int index) throws IllegalAccessException {
        logger.log(Level.WARN, "getByIndex() is not available");
        throw new IllegalAccessException();
    }

    @Override
    public int size() throws IllegalAccessException {
        logger.log(Level.WARN, "size() is not available");
        throw new IllegalAccessException();
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    public boolean isLetterOrDigit() {
        return Character.isLetterOrDigit(symbol);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
