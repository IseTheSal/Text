package by.learning.project.composite.component;

public interface TextComponent {
    boolean add(TextComponent component) throws IllegalAccessException;

    boolean remove(TextComponent component) throws IllegalAccessException;

    TextComponent getByIndex(int index) throws IllegalAccessException;

    int size() throws IllegalAccessException;

    ComponentType getComponentType();
}
