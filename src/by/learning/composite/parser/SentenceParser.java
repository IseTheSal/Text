package by.learning.composite.parser;

import by.learning.composite.component.ComponentType;
import by.learning.composite.component.TextComponent;
import by.learning.composite.component.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);

    private static final String SENTENCE_REGEX =
            "(([.?!…]\\n\\s{4})+|[.?!…]\\s)+|([.?!…]\t)+|([.?!…]\\s{4})+|([.?!…]\\n)+";

    public SentenceParser(TextParser next) {
        super(next);
        if (next == null) {
            logger.log(Level.DEBUG, "Next parser is null");
            setNext(new SymbolParser());
        }
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent result = new TextComposite(ComponentType.PARAGRAPH);
        List<String> sentences = parseText(text);
        for (String sentence : sentences) {
            TextComponent nextComponent = next.parse(sentence);
            result.add(nextComponent);
        }
        return result;
    }

    private List<String> parseText(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);
        int index = 0;
        String buffer;
        while (matcher.find(index)) {
            buffer = text.substring(index, matcher.start() + 1);
            result.add(buffer);
            index = matcher.end();
        }
        buffer = text.substring(index);
        result.add(buffer);
        return result;
    }
}
