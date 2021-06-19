package by.boginsky.entity;

public class Tablet extends AbstractCommonMedicine {

    private boolean engraving;

    public Tablet() {
    }

    public Tablet(String nameOfMedicine, String groupOfMedicine, Manufacturer manufacturer, String analogs, Version version, Form form, boolean engraving) {
        super(nameOfMedicine, groupOfMedicine, manufacturer, analogs, version, form);
        this.engraving = engraving;
    }

    public boolean isEngraving() {
        return engraving;
    }

    public void setEngraving(boolean engraving) {
        this.engraving = engraving;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Tablet tablet = (Tablet) o;

        return engraving == tablet.engraving;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return 31 * result + (engraving ? 1 : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tablet{");
        sb.append("engraving=").append(engraving);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder { // FIXME: 14.06.2021 make default
        private Tablet tablet;

        private Builder() {
            tablet = new Tablet();
        }

        public Builder nameOfMedicineSet(String nameOfMedicine) {
            tablet.setNameOfMedicine(nameOfMedicine);
            return this;
        }

        public Builder groupOfMedicineSet(String groupOfMedicine) {
            tablet.setGroupOfMedicine(groupOfMedicine);
            return this;
        }

        public Builder manufacturerSet(Manufacturer manufacturer) {
            tablet.setManufacturer(manufacturer);
            return this;
        }

        public Builder analogsSet(String analogs) {
            tablet.setAnalogs(analogs);
            return this;
        }

        public Builder versionSet(Version version) {
            tablet.setVersion(version);
            return this;
        }

        public Builder formSet(Form form) {
            tablet.setForm(form);
            return this;
        }

        public Builder engravingSet(boolean engraving) {
            tablet.setEngraving(engraving);
            return this;
        }

        public Tablet build() {
            return tablet;
        }
    }
}
