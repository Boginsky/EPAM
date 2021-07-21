package entity.impl;

import entity.Component;
import entity.ComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LetterNode implements Component {
    private static Logger logger = LogManager.getLogger();
    private ComponentType type;
    private char symbol;

    public LetterNode(ComponentType type, char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    @Override
    public List<Component> getAllComponents() {
        logger.error("Cant do this operation on node");
        throw new UnsupportedOperationException("Unsupported operation get children on leaf");
    }

    @Override
    public void addTextComponent(Component component) {
        logger.error("Cant do this operation on node");
        throw new UnsupportedOperationException("Unsupported operation add on leaf");
    }

    @Override
    public void removeComponent(Component component) {
        logger.error("Cant do this operation on node");
        throw new UnsupportedOperationException("Unsupported operation remove on leaf");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetterNode)) return false;
        LetterNode that = (LetterNode) o;
        return symbol == that.symbol && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (int) symbol;
        return result;
    }

    @Override
    public String toString() {
      return Character.toString(symbol);
    }
}
