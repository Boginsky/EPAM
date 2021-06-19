package by.boginsky.parser;

public enum MedicineXmlTag {
    MEDICINES,
    MEDICINE,
    TABLET,
    CAPSULE,
    SOLUTION,
    SOLVENT,
    NAME_OF_MEDICINE,
    GROUP_OF_MEDICINE,
    MANUFACTURER,
    ANALOGS,
    VERSION,
    CERTIFICATE,
    CERTIFICATEID,
    ID,
    FORM,
    MODIFIED,
    ENGRAVING,
    DOSAGE,
    DOSAGE_OF_MEDICINE,
    FREQUENCY_OF_ADMISSION,
    PRICE,
    AMOUNT_OF_MEDICINE,
    TYPE_OF_PACKAGE,
    PACKAGE,
    DATE_OF_ISSUE,
    COUNTRY_OF_REGISTRATION;

    public String tagName() {
        return name().replace("_", "-").toLowerCase();
    }
}
