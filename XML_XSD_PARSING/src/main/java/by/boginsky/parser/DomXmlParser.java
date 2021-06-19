package by.boginsky.parser;

import by.boginsky.entity.Package;
import by.boginsky.entity.*;
import by.boginsky.exception.MedicineException;
import by.boginsky.validator.CustomFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static by.boginsky.parser.MedicineXmlTag.*;

public class DomXmlParser implements XmlParser {
    private static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Set<AbstractCommonMedicine> medicines;
    private final static int TAG_POSITION = 0;
    private final static boolean POISONOUS_DEFAULT = false;

    public DomXmlParser() {
        medicines = new HashSet<>();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Exception from parser" + e.getMessage());
        }
    }

    public Set<AbstractCommonMedicine> parserXml(String filePath) throws MedicineException {
        Document document = null;
        if (!CustomFileValidator.isFileValid(filePath)) {
            logger.log(Level.ERROR, "File invalid");
            throw new MedicineException("File invalid");
        }
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();

            NodeList tabletList = root.getElementsByTagName(MedicineXmlTag.TABLET.tagName());
            parserNodeList(tabletList, MedicineXmlTag.TABLET.tagName());

            NodeList capsuleList = root.getElementsByTagName(MedicineXmlTag.CAPSULE.tagName());
            parserNodeList(capsuleList, MedicineXmlTag.CAPSULE.tagName());

            NodeList solutionList = root.getElementsByTagName(MedicineXmlTag.SOLUTION.tagName());
            parserNodeList(solutionList, MedicineXmlTag.SOLUTION.tagName());
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "DomParser error: " + e.getMessage());
            throw new MedicineException("DomParser failed");
        }
        return medicines;
    }

    private void parserNodeList(NodeList nodeList, String type) {
        if (MedicineXmlTag.TABLET.tagName().equals(type)) {
            tabletMaker(nodeList);
        } else if (MedicineXmlTag.CAPSULE.tagName().equals(type)) {
            capsuleMaker(nodeList);
        } else {
            solutionMaker(nodeList);
        }
    }

    private void tabletMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Tablet.Builder builder = Tablet.builder();
            Element element = (Element) nodeList.item((i));

            builder.nameOfMedicineSet(getContent(element, NAME_OF_MEDICINE.tagName()))
                    .groupOfMedicineSet(getContent(element, GROUP_OF_MEDICINE.tagName()))
                    .manufacturerSet(Manufacturer.valueOf(enumName(getContent(element, MANUFACTURER.tagName()))))
                    .analogsSet(getContent(element, ANALOGS.tagName()))
                    .versionSet(versionMaker(element, PACKAGE.tagName(), CERTIFICATE.tagName(), DOSAGE.tagName()))
                    .formSet(Form.valueOf(enumName(getContent(element, FORM.tagName()))))
                    .engravingSet(Boolean.parseBoolean(getContent(element, ENGRAVING.tagName())));
            medicines.add(builder.build());
        }
    }

    private void capsuleMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Capsule.Builder builder = Capsule.builder();
            Element element = (Element) nodeList.item(i);

            builder.nameOfMedicineSet(getContent(element, NAME_OF_MEDICINE.tagName()))
                    .groupOfMedicineSet(getContent(element, GROUP_OF_MEDICINE.tagName()))
                    .manufacturerSet(Manufacturer.valueOf(enumName(getContent(element, MANUFACTURER.tagName()))))
                    .analogsSet(getContent(element, ANALOGS.tagName()))
                    .versionSet(versionMaker(element, PACKAGE.tagName(), CERTIFICATE.tagName(), DOSAGE.tagName()))
                    .formSet(Form.valueOf(enumName(getContent(element, FORM.tagName()))))
                    .modifiedSet(Boolean.parseBoolean(getContent(element, MODIFIED.tagName())));
            medicines.add(builder.build());
        }
    }

    private void solutionMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Solution.Builder builder = Solution.builder();
            Element element = (Element) nodeList.item(i);


            builder.nameOfMedicineSet(getContent(element, NAME_OF_MEDICINE.tagName()))
                    .groupOfMedicineSet(getContent(element, GROUP_OF_MEDICINE.tagName()))
                    .manufacturerSet(Manufacturer.valueOf(enumName(getContent(element, MANUFACTURER.tagName()))))
                    .analogsSet(getContent(element, ANALOGS.tagName()))
                    .versionSet(versionMaker(element, PACKAGE.tagName(), CERTIFICATE.tagName(), DOSAGE.tagName()))
                    .formSet(Form.valueOf(enumName(getContent(element, FORM.tagName()))))
                    .solventSet(Solvent.valueOf(enumName(getContent(element, SOLVENT.tagName()))));
            medicines.add(builder.build());
        }
    }

    private Version versionMaker(Element element, String elementPackage, String elementCertificate, String elementDosage) {
        NodeList nodeListFull = element.getElementsByTagName(elementPackage);
        Element elementLocal = (Element) nodeListFull.item(TAG_POSITION);

        Package.Builder packageBuilder = Package.builder();
        packageBuilder.typeOfPackageSet(getContent(elementLocal, TYPE_OF_PACKAGE.tagName()))
                .amountOfMedicineSet(Integer.parseInt(getContent(elementLocal, AMOUNT_OF_MEDICINE.tagName())))
                .priceSet(Float.parseFloat(getContent(elementLocal, PRICE.tagName())));

        nodeListFull = element.getElementsByTagName(elementDosage);
        elementLocal = (Element) nodeListFull.item(TAG_POSITION);

        Dosage.Builder dosageBuilder = Dosage.builder();
        dosageBuilder.dosageOfMedicineSet(Float.parseFloat(getContent(elementLocal, DOSAGE_OF_MEDICINE.tagName())))
                .frequencyOfAdmissionSet(Integer.parseInt(getContent(elementLocal, FREQUENCY_OF_ADMISSION.tagName())));

        nodeListFull = element.getElementsByTagName(elementCertificate);
        elementLocal = (Element) nodeListFull.item(TAG_POSITION);

        Certificate.Builder certificateBuilder = Certificate.builder();
        certificateBuilder.idSet(nodeListFull.item(TAG_POSITION).getAttributes().getNamedItem(ID.tagName()).getNodeValue())
                .dateOfIssueSet(LocalDate.parse(getContent(elementLocal, DATE_OF_ISSUE.tagName())))
                .countryOfRegistrationSet(nodeListFull.item(TAG_POSITION).getAttributes().getNamedItem(COUNTRY_OF_REGISTRATION.tagName()).getNodeValue());

        Version.Builder versionBuilder = Version.builder();

        return  versionBuilder.setInfoAboutPackage(packageBuilder.build())
                .setDosage(dosageBuilder.build())
                .setCertificate(certificateBuilder.build())
                .build();
    }

    private String getContent(Element elementOfFlower, String elementName) {
        NodeList nodeListFull = elementOfFlower.getElementsByTagName(elementName);
        Node nodeElement = nodeListFull.item(TAG_POSITION);
        String result = nodeElement.getChildNodes().item(TAG_POSITION).getNodeValue();
        return result;
    }

    private static String enumName(String enumName) {
        return enumName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}

