package by.learning.project.composite.component;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private static final char SPACE = ' ';
    private static final String PARAGRAPH_SEPARATOR = "    ";
    private static final String NEW_LINE_SYMBOL = "\n";

    private final ComponentType componentType;
    private final List<TextComponent> components;

    public TextComposite(ComponentType componentType) {
        components = new ArrayList<>();
        this.componentType = componentType;
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public TextComponent getByIndex(int index) {
        return components.get(index);
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (!components.isEmpty()) {
            TextComponent firstComponent = components.get(0);
            ComponentType firstComponentType = firstComponent.getComponentType();
            if (firstComponentType == ComponentType.PARAGRAPH
                    || firstComponentType == ComponentType.TEXT) {
                sb.append(PARAGRAPH_SEPARATOR);
            }
            sb.append(firstComponent.toString());

            for (int i = 1; i < components.size(); i++) {
                TextComponent component = components.get(i);
                ComponentType componentType = component.getComponentType();
                if (componentType == ComponentType.PARAGRAPH
                        || componentType == ComponentType.TEXT) {
                    sb.append(NEW_LINE_SYMBOL).append(PARAGRAPH_SEPARATOR);
                } else if (componentType != ComponentType.SYMBOL) {
                    sb.append(SPACE);
                }
                sb.append(component.toString());
            }
        }
        return sb.toString();
    }
}
