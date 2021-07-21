package entity.impl;

import entity.Component;
import entity.ComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements Component {
    private static Logger logger = LogManager.getLogger();
    private static final String TABULATION_REGEX = "\t";
    private List<Component> componentList = new ArrayList<>();
    private ComponentType type;

    public TextComponent(ComponentType type) {
        this.type = type;
    }

    @Override
    public List<Component> getAllComponents() {
        return componentList;
    }

    @Override
    public void addTextComponent(Component component) {
        componentList.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        componentList.remove(component);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComponent)) return false;
        TextComponent that = (TextComponent) o;
        return componentList.equals(that.componentList) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = componentList != null ? componentList.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextComponent{");
        sb.append("componentList=").append(componentList);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
