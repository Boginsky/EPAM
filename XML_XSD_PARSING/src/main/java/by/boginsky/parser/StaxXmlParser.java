package by.boginsky.parser;

import by.boginsky.entity.Package;
import by.boginsky.entity.*;
import by.boginsky.exception.MedicineException;
import by.boginsky.validator.CustomFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static by.boginsky.parser.MedicineXmlTag.*;

public class StaxXmlParser implements XmlParser {

    private static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    private Set<AbstractCommonMedicine> medicines;
    private XMLInputFactory xmlInputFactory;
    private XMLEventReader xmlEventReader;
    private Map<String, String> elementMap;
    private MedicineXmlTag makeMedicine;
    private MedicineXmlTag element;

    public StaxXmlParser() {
        xmlInputFactory = XMLInputFactory.newFactory();
        elementMap = new HashMap<>();
        medicines = new HashSet<>();
    }

    @Override
    public Set<AbstractCommonMedicine> parserXml(String filePath) throws MedicineException {
        if (!CustomFileValidator.isFileValid(filePath)) {
            logger.log(Level.ERROR, "Invalid File");
            throw new MedicineException("Invalid File");
        }

        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
            while (xmlEventReader.hasNext()) {
                XMLEvent event = null;
                try {
                    event = xmlEventReader.nextEvent();
                } catch (XMLStreamException e) {
                    logger.log(Level.ERROR, "Error in event");
                    throw new MedicineException("Error in event");
                }
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    MedicineXmlTag medicineType = MedicineXmlTag.valueOf(tagToEnum(startElement.getName().getLocalPart()));
                    switch (medicineType) {
                        case TABLET:
                        case SOLUTION:
                        case CAPSULE:
                            makeMedicine = medicineType;
                            break;
                        default:
                            if(medicineType.equals(CERTIFICATE)) {
                                getAttribute(startElement);
                            }
                            element = medicineType;
                            event = xmlEventReader.nextEvent();
                            eventHandler(event);
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    MedicineXmlTag endMedicine = MedicineXmlTag.valueOf(tagToEnum(endElement.getName().getLocalPart()));
                    switch (endMedicine) {
                        case TABLET:
                        case CAPSULE:
                        case SOLUTION:
                            medicineMaker();
                            break;
                    }
                }
            }
            return medicines;
        } catch (XMLStreamException | IOException e) {
            logger.log(Level.ERROR, "Error in parsing");
            throw new MedicineException("Error in parsing");
        }
    }

    private void medicineMaker() {
        switch (makeMedicine) {
            case TABLET:
                Tablet.Builder tabletBuilder = Tablet.builder();
                tabletBuilder.nameOfMedicineSet(elementMap.get(NAME_OF_MEDICINE.tagName()))
                        .groupOfMedicineSet(elementMap.get(GROUP_OF_MEDICINE.tagName()))
                        .manufacturerSet(Manufacturer.valueOf(tagToEnum(elementMap.get(MANUFACTURER.tagName()))))
                        .analogsSet(elementMap.get(ANALOGS.tagName()))
                        .versionSet(versionMaker())
                        .formSet(Form.valueOf(tagToEnum(elementMap.get(FORM.tagName()))))
                        .engravingSet(Boolean.parseBoolean(elementMap.get(ENGRAVING.tagName())));
                medicines.add(tabletBuilder.build());
                break;
            case CAPSULE:
                Capsule.Builder capsuleBuilder = Capsule.builder();
                capsuleBuilder.nameOfMedicineSet(elementMap.get(NAME_OF_MEDICINE.tagName()))
                        .groupOfMedicineSet(elementMap.get(GROUP_OF_MEDICINE.tagName()))
                        .manufacturerSet(Manufacturer.valueOf(tagToEnum(elementMap.get(MANUFACTURER.tagName()))))
                        .analogsSet(elementMap.get(ANALOGS.tagName()))
                        .versionSet(versionMaker())
                        .formSet(Form.valueOf(tagToEnum(elementMap.get(FORM.tagName()))))
                        .modifiedSet(Boolean.parseBoolean(elementMap.get(MODIFIED.tagName())));
                medicines.add(capsuleBuilder.build());
                break;
            case SOLUTION:
                Solution.Builder solutionBuilder = Solution.builder();
                solutionBuilder.nameOfMedicineSet(elementMap.get(NAME_OF_MEDICINE.tagName()))
                        .groupOfMedicineSet(elementMap.get(GROUP_OF_MEDICINE.tagName()))
                        .manufacturerSet(Manufacturer.valueOf(tagToEnum(elementMap.get(MANUFACTURER.tagName()))))
                        .analogsSet(elementMap.get(ANALOGS.tagName()))
                        .versionSet(versionMaker())
                        .formSet(Form.valueOf(tagToEnum(elementMap.get(FORM.tagName()))))
                        .solventSet(Solvent.valueOf(tagToEnum(elementMap.get(SOLVENT.tagName()))));
                medicines.add(solutionBuilder.build());
                break;
        }
    }

    private Version versionMaker() {
        Version.Builder versionBuilder = Version.builder();
        Certificate.Builder certificateBuilder = Certificate.builder();
        Package.Builder packageBuilder = Package.builder();
        Dosage.Builder dosageBuilder = Dosage.builder();

        certificateBuilder.idSet(elementMap.get(ID.tagName()))
                .countryOfRegistrationSet(elementMap.get(COUNTRY_OF_REGISTRATION.tagName()))
                .dateOfIssueSet(LocalDate.parse(elementMap.get(DATE_OF_ISSUE.tagName())));


        packageBuilder.priceSet(Float.parseFloat(elementMap.get(PRICE.tagName())))
                .amountOfMedicineSet(Integer.parseInt(elementMap.get(AMOUNT_OF_MEDICINE.tagName())))
                .typeOfPackageSet(elementMap.get(TYPE_OF_PACKAGE.tagName()));

        dosageBuilder.dosageOfMedicineSet(Float.parseFloat(elementMap.get(DOSAGE_OF_MEDICINE.tagName())))
                .frequencyOfAdmissionSet(Integer.parseInt(elementMap.get(FREQUENCY_OF_ADMISSION.tagName())));

        return versionBuilder.setDosage(dosageBuilder.build())
                .setCertificate(certificateBuilder.build())
                .setInfoAboutPackage(packageBuilder.build())
                .build();
    }


    private void eventHandler(XMLEvent event) {
        switch (element) {
            case NAME_OF_MEDICINE:
            case ENGRAVING:
            case SOLVENT:
            case FORM:
            case MODIFIED:
            case FREQUENCY_OF_ADMISSION:
            case DOSAGE_OF_MEDICINE:
            case PRICE:
            case AMOUNT_OF_MEDICINE:
            case TYPE_OF_PACKAGE:
            case DATE_OF_ISSUE:
            case ANALOGS:
            case MANUFACTURER:
            case GROUP_OF_MEDICINE:
                elementMap.put(element.tagName(), event.asCharacters().getData());
                break;
        }
    }

    private void getAttribute(StartElement startElement) {
        switch (makeMedicine) {
            case TABLET:
            case CAPSULE:
            case SOLUTION:
                elementMap.put(ID.tagName(), startElement.getAttributeByName(new QName(ID.tagName())).getValue());
                elementMap.put(COUNTRY_OF_REGISTRATION.tagName(), startElement.getAttributeByName(new QName(COUNTRY_OF_REGISTRATION.tagName())).getValue());
                break;
        }
    }

    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
