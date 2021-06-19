package by.boginsky.parser.factory.impl;

import by.boginsky.parser.StaxXmlParser;
import by.boginsky.parser.XmlParser;
import by.boginsky.parser.factory.XmlParserFactory;

public class StaxXmlParserFactory implements XmlParserFactory {
    @Override
    public XmlParser newParser() {
        return new StaxXmlParser();
    }
}
