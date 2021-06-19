package by.boginsky.parser;

import by.boginsky.entity.AbstractCommonMedicine;
import by.boginsky.exception.MedicineException;

import java.util.Set;

public interface XmlParser {
    public Set<AbstractCommonMedicine> parserXml(String filePath) throws MedicineException;
}
