package parser;

import entity.Component;
import exception.CustomException;

public interface TextParser {
    public Component parse(String text) throws CustomException;
}
