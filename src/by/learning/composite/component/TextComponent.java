package by.learning.composite.component;

public interface TextComponent {
    boolean add(TextComponent component);

    TextComponent getByIndex(int index);

    int size();

    ComponentType getComponentType();
}
