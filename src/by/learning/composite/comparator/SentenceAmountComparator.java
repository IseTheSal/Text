package by.learning.composite.comparator;

import by.learning.composite.component.TextComponent;
import by.learning.project.composite.component.TextComponent;

import java.util.Comparator;

public class SentenceAmountComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        return Integer.compare(o1.size(), o2.size());
    }
}
