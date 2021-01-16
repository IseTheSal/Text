package by.learning.project.composite.parser;

import by.learning.project.composite.component.ComponentType;
import by.learning.project.composite.component.TextComponent;
import by.learning.project.composite.component.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParagraphParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String PARAGRAPH_REGEX = "\\n(\\s){4}|\\s{4}|[\\t]+";

    public ParagraphParser(TextParser next) {
        super(next);
        if (next == null) {
            logger.log(Level.DEBUG, "Next parser is null.");
            setNext(new SymbolParser());
        }
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent result = new TextComposite(ComponentType.TEXT);
        List<String> paragraphs = parseText(text);
        for (String paragraph : paragraphs) {
            TextComponent nextComponent = next.parse(paragraph);
            result.add(nextComponent);
        }
        return result;
    }

    private List<String> parseText(String text) {
        String[] splitText = text.split(PARAGRAPH_REGEX);
        List<String> result = Arrays.stream(splitText).filter(x -> !x.equals("")).collect(Collectors.toList());
        return result;
    }
}
