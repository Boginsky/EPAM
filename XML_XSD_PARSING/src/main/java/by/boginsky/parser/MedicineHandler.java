package by.boginsky.parser;

import by.boginsky.entity.Package;
import by.boginsky.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static by.boginsky.parser.MedicineXmlTag.*;

public class MedicineHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    private Set<AbstractCommonMedicine> medicines = new HashSet<>();
    private Tablet.Builder tabletBuilder;
    private Capsule.Builder capsuleBuilder;
    private Solution.Builder solutionBuilder;
    private Version.Builder versionBuilder;
    private Certificate.Builder certificateBuilder;
    private Package.Builder packageBuilder;
    private Dosage.Builder dosageBuilder;

    private MedicineXmlTag medicineXmlTag;
    private MedicineXmlTag nextXmlTag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        MedicineXmlTag enumTag = MedicineXmlTag.valueOf(tagToEnum(qName));
        switch (enumTag) {
            case TABLET:
                medicineXmlTag = TABLET;
                versionBuilder = Version.builder();
                certificateBuilder = Certificate.builder();
                packageBuilder = Package.builder();
                dosageBuilder = Dosage.builder();
                tabletBuilder = Tablet.builder();
                break;
            case CAPSULE:
                medicineXmlTag = CAPSULE;
                capsuleBuilder = Capsule.builder();
                break;
            case SOLUTION:
                medicineXmlTag = SOLUTION;
                solutionBuilder = Solution.builder();
                break;
            case CERTIFICATE:
                attributesSet(attributes);
                break;
            default:
                nextXmlTag = enumTag;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        MedicineXmlTag enumTag = MedicineXmlTag.valueOf(tagToEnum(qName));
        switch (enumTag) {
            case TABLET:
                medicines.add(tabletBuilder.build());
                break;
            case CAPSULE:
                medicines.add(capsuleBuilder.build());
                break;
            case SOLUTION:
                medicines.add(solutionBuilder.build());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String data = new String(ch, start, length).trim();
        if (data != null && data.length() > 0) {
            if (medicineXmlTag == TABLET) {
                switch (nextXmlTag) {
                    case NAME_OF_MEDICINE:
                        tabletBuilder.nameOfMedicineSet(data);
                        break;
                    case GROUP_OF_MEDICINE:
                        tabletBuilder.groupOfMedicineSet(data);
                        break;
                    case MANUFACTURER:
                        tabletBuilder.manufacturerSet(Manufacturer.valueOf(tagToEnum(data)));
                        break;
                    case ANALOGS:
                        tabletBuilder.analogsSet(data);
                        break;
                    case DATE_OF_ISSUE:
                        certificateBuilder.dateOfIssueSet(LocalDate.parse(data));
                        versionBuilder.setCertificate(certificateBuilder.build());
                        break;
                    case TYPE_OF_PACKAGE:
                        packageBuilder.typeOfPackageSet(data);
                        break;
                    case AMOUNT_OF_MEDICINE:
                        packageBuilder.amountOfMedicineSet(Integer.parseInt(data));
                        break;
                    case PRICE:
                        packageBuilder.priceSet(Float.parseFloat(data));
                        versionBuilder.setInfoAboutPackage(packageBuilder.build());
                        break;
                    case DOSAGE_OF_MEDICINE:
                        dosageBuilder.dosageOfMedicineSet(Float.parseFloat(data));
                        break;
                    case FREQUENCY_OF_ADMISSION:
                        dosageBuilder.frequencyOfAdmissionSet(Integer.parseInt(data));
                        versionBuilder.setDosage(dosageBuilder.build());
                        tabletBuilder.versionSet(versionBuilder.build());
                        break;
                    case FORM:
                        tabletBuilder.formSet(Form.valueOf(tagToEnum(data)));
                        break;
                    case ENGRAVING:
                        tabletBuilder.engravingSet(Boolean.parseBoolean(data));
                        break;
                    default:
                        throw new SAXException("There is no such constant " + nextXmlTag.getDeclaringClass() + " " + nextXmlTag.tagName());
                }
            } else if (medicineXmlTag == CAPSULE) {
                switch (nextXmlTag) {
                    case NAME_OF_MEDICINE:
                        capsuleBuilder.nameOfMedicineSet(data);
                        break;
                    case GROUP_OF_MEDICINE:
                        capsuleBuilder.groupOfMedicineSet(data);
                        break;
                    case MANUFACTURER:
                        capsuleBuilder.manufacturerSet(Manufacturer.valueOf(tagToEnum(data)));
                        break;
                    case ANALOGS:
                        capsuleBuilder.analogsSet(data);
                        break;
                    case DATE_OF_ISSUE:
                        certificateBuilder.dateOfIssueSet(LocalDate.parse(data));
                        versionBuilder.setCertificate(certificateBuilder.build());
                        break;
                    case TYPE_OF_PACKAGE:
                        packageBuilder.typeOfPackageSet(data);
                        break;
                    case AMOUNT_OF_MEDICINE:
                        packageBuilder.amountOfMedicineSet(Integer.parseInt(data));
                        break;
                    case PRICE:
                        packageBuilder.priceSet(Float.parseFloat(data));
                        versionBuilder.setInfoAboutPackage(packageBuilder.build());
                        break;
                    case DOSAGE_OF_MEDICINE:
                        dosageBuilder.dosageOfMedicineSet(Float.parseFloat(data));
                        break;
                    case FREQUENCY_OF_ADMISSION:
                        dosageBuilder.frequencyOfAdmissionSet(Integer.parseInt(data));
                        versionBuilder.setDosage(dosageBuilder.build());
                        capsuleBuilder.versionSet(versionBuilder.build());
                        break;
                    case FORM:
                        capsuleBuilder.formSet(Form.valueOf(tagToEnum(data)));
                        break;
                    case MODIFIED:
                        capsuleBuilder.modifiedSet(Boolean.parseBoolean(data));
                        break;
                    default:
                        throw new SAXException("There is no such constant " + nextXmlTag.getDeclaringClass() + " " + nextXmlTag.tagName());
                }
            }else {
                switch (nextXmlTag) {
                    case NAME_OF_MEDICINE:
                        solutionBuilder.nameOfMedicineSet(data);
                        break;
                    case GROUP_OF_MEDICINE:
                        solutionBuilder.groupOfMedicineSet(data);
                        break;
                    case MANUFACTURER:
                        solutionBuilder.manufacturerSet(Manufacturer.valueOf(tagToEnum(data)));
                        break;
                    case ANALOGS:
                        solutionBuilder.analogsSet(data);
                        break;
                    case DATE_OF_ISSUE:
                        certificateBuilder.dateOfIssueSet(LocalDate.parse(data));
                        versionBuilder.setCertificate(certificateBuilder.build());
                        break;
                    case TYPE_OF_PACKAGE:
                        packageBuilder.typeOfPackageSet(data);
                        break;
                    case AMOUNT_OF_MEDICINE:
                        packageBuilder.amountOfMedicineSet(Integer.parseInt(data));
                        break;
                    case PRICE:
                        packageBuilder.priceSet(Float.parseFloat(data));
                        versionBuilder.setInfoAboutPackage(packageBuilder.build());
                        break;
                    case DOSAGE_OF_MEDICINE:
                        dosageBuilder.dosageOfMedicineSet(Float.parseFloat(data));
                        break;
                    case FREQUENCY_OF_ADMISSION:
                        dosageBuilder.frequencyOfAdmissionSet(Integer.parseInt(data));
                        versionBuilder.setDosage(dosageBuilder.build());
                        solutionBuilder.versionSet(versionBuilder.build());
                        break;
                    case FORM:
                        solutionBuilder.formSet(Form.valueOf(tagToEnum(data)));
                        break;
                    case SOLVENT:
                        solutionBuilder.solventSet(Solvent.valueOf(tagToEnum(data)));
                        break;
                    default:
                        throw new SAXException("There is no such constant " + nextXmlTag.getDeclaringClass() + " " + nextXmlTag.tagName());
                }
            }
        }
    }

    public Set<AbstractCommonMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<AbstractCommonMedicine> medicines) {
        this.medicines = medicines;
    }

    private void attributesSet(Attributes attributes) {
        certificateBuilder.idSet(attributes.getValue(ID.tagName()))
                .countryOfRegistrationSet(attributes.getValue(COUNTRY_OF_REGISTRATION.tagName()));
    }

    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
