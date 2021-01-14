package by.learning.project.composite.parser;

import by.learning.project.composite.component.ComponentType;
import by.learning.project.composite.component.TextComponent;
import by.learning.project.composite.component.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WordParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger(WordParser.class);
    private static final String WORD_REGEX = "\\s+";

    public WordParser(TextParser next) {
        super(next);
        if (next == null) {
            LOGGER.log(Level.DEBUG, "Next parser is null");
            setNext(new SymbolParser());
        }
    }

    @Override
    public TextComponent parse(String text) throws IllegalAccessException {
        TextComponent result = new TextComposite(ComponentType.SENTENCE);
        List<String> words = parseText(text);
        for (String word : words) {
            TextComponent nextComponent = next.parse(word);
            result.add(nextComponent);
        }
        return result;
    }

    private List<String> parseText(String text) {
        String[] splitText = text.split(WORD_REGEX);
        List<String> result = new ArrayList<>();
        for (String word : splitText) {
            if (word.isBlank()) {
                continue;
            }
            result.add(word);
        }
        return result;
    }
}
