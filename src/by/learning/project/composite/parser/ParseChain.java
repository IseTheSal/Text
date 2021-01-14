package by.learning.project.composite.parser;

import by.learning.project.composite.component.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseChain implements TextParser {
    private static final Logger logger = LogManager.getLogger(ParseChain.class);

    private static final TextParser instance = new ParseChain();
    private final TextParser symbolParser = new SymbolParser();
    private final TextParser wordParser = new WordParser(symbolParser);
    private final TextParser sentenceParser = new SentenceParser(wordParser);
    private final TextParser paragraphParser = new ParagraphParser(sentenceParser);

    private ParseChain() {
    }

    public static TextParser getInstance() {
        return instance;
    }

    @Override
    public TextComponent parse(String text) throws IllegalAccessException {
        TextComponent result = paragraphParser.parse(text);
        logger.log(Level.DEBUG, "Parsing text is complete");
        return result;
    }
}
