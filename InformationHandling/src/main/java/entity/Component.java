package entity;

import java.util.List;

public interface Component {

    public List<Component> getAllComponents();

    void addTextComponent(Component component);

    public void removeComponent(Component component);

    ComponentType getType();
}
