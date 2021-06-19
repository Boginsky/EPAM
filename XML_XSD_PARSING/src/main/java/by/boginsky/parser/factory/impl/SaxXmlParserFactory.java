package by.boginsky.parser.factory.impl;

import by.boginsky.parser.XmlParser;
import by.boginsky.parser.factory.XmlParserFactory;
import by.boginsky.parser.SaxXmlParser;

public class SaxXmlParserFactory implements XmlParserFactory {

    @Override
    public XmlParser newParser() {
        return new SaxXmlParser();
    }
}
