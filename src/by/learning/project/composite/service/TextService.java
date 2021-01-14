package by.learning.project.composite.service;

import by.learning.project.composite.component.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class TextService {

    public List<TextComponent> sortTextByParagraphs(TextComponent text, Comparator<TextComponent> comparator) throws IllegalAccessException {
        List<TextComponent> result = new ArrayList<>();
        for (int i = 0; i < text.size(); i++) {
            TextComponent component = text.getByIndex(i);
            result.add(component);
        }
        result.sort(comparator);
        return result;
    }

    public HashMap<String, Integer> findAllSimilarWords(TextComponent text) throws IllegalAccessException {
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < text.size(); i++) {
            TextComponent component = text.getByIndex(i);
            String stringComponent = component.toString();
            Integer value = wordMap.get(stringComponent);
            value++;
            wordMap.put(stringComponent, value);
        }
        HashMap<String, Integer> result = new HashMap<>();
        for (int i = 0; i < text.size(); i++) {
            TextComponent component = text.getByIndex(i);
            String stringComponent = component.toString();
            Integer value = wordMap.get(stringComponent);
            if (value > 1) {
                result.put(stringComponent, value);
            }
        }
        return result;
    }
}
