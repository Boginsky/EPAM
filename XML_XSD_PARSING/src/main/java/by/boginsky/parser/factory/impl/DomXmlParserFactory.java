package by.boginsky.parser.factory.impl;

import by.boginsky.parser.DomXmlParser;
import by.boginsky.parser.XmlParser;
import by.boginsky.parser.factory.XmlParserFactory;

public class DomXmlParserFactory implements XmlParserFactory {
    @Override
    public XmlParser newParser() {
        return new DomXmlParser();
    }
}
