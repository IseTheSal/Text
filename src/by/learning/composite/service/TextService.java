package by.learning.composite.service;

import by.learning.composite.component.ComponentType;
import by.learning.composite.component.TextComponent;
import by.learning.composite.component.TextComposite;
import by.learning.composite.parser.*;
import by.learning.project.composite.parser.*;

import java.util.*;

public class TextService {

    public List<TextComponent> sortText(TextComponent text, Comparator<TextComponent> comparator) {
        List<TextComponent> result = new ArrayList<>();
        for (int i = 0; i < text.size(); i++) {
            TextComponent component = text.getByIndex(i);
            result.add(component);
        }
        result.sort(comparator);
        return result;
    }

    public HashMap<String, Integer> findAllSimilarWords(String text) throws IllegalAccessException {
        HashMap<String, Integer> wordMap = new HashMap<>();
        TextParser textParser = new WordParser(new SymbolParser());
        TextComponent parsedText = textParser.parse(text);
        for (int i = 0; i < parsedText.size(); i++) {
            TextComponent component = parsedText.getByIndex(i);
            String stringComponent = component.toString();
            int value = 0;
            if (wordMap.get(stringComponent) != null) {
                value = wordMap.get(stringComponent);
                value++;
            }
            wordMap.put(stringComponent, value);
        }
        HashMap<String, Integer> result = new HashMap<>();
        for (int i = 0; i < parsedText.size(); i++) {
            TextComponent component = parsedText.getByIndex(i);
            String stringComponent = component.toString();
            Integer value = wordMap.get(stringComponent);
            if (value > 1) {
                result.put(stringComponent, value);
            }
        }
        return result;
    }

    public Set<TextComponent> findSentencesWithLongestWords(String text) {
        Set<TextComponent> result = new HashSet<>();
        TextParser wordParser = new WordParser(new SymbolParser());
        TextParser sentenceParser = new SentenceParser(wordParser);
        TextComponent parsedText = sentenceParser.parse(text);
        int maxCharacters = 0;
        for (int i = 0; i < parsedText.size(); i++) {
            TextComponent component = parsedText.getByIndex(i);
            TextComposite textComposite = new TextComposite(ComponentType.WORD);
            textComposite.add(component);
            String sentenceString = textComposite.toString();
            TextComponent parsedSentence = wordParser.parse(sentenceString);
            for (int j = 0; j < parsedSentence.size(); j++) {
                TextComponent word = parsedSentence.getByIndex(j);
                if (maxCharacters < word.size()) {
                    maxCharacters = word.size();
                    result.clear();
                    result.add(component);
                } else if (maxCharacters == word.size()) {
                    result.add(component);
                }
            }
        }
        return result;
    }

    public String removeSentenceByWordAmount(String text, int amount) {
        StringBuilder result = new StringBuilder();
        TextParser wordParser = new WordParser(new SymbolParser());
        TextParser sentenceParser = new SentenceParser(wordParser);
        TextParser paragraphParser = new ParagraphParser(sentenceParser);
        TextComponent parsedText = paragraphParser.parse(text);
        for (int i = 0; i < parsedText.size(); i++) {
            TextComponent component = parsedText.getByIndex(i);
            TextComposite textComposite = new TextComposite(ComponentType.SENTENCE);
            textComposite.add(component);
            String paragraphString = textComposite.toString();
            TextComponent parsedParagraph = sentenceParser.parse(paragraphString);
            for (int j = 0; j < parsedParagraph.size(); j++) {
                TextComponent paragraphComponent = parsedParagraph.getByIndex(j);
                int size = paragraphComponent.size();
                if (size >= amount) {
                    result.append(paragraphComponent);
                }
            }
        }
        return result.toString();
    }

}
