package by.boginsky.entity;

public class Capsule extends AbstractCommonMedicine {

    private boolean modified;

    public Capsule() {
    }

    public Capsule(String nameOfMedicine, String groupOfMedicine, Manufacturer manufacturer, String analogs, Version version, Form form, boolean modified) {
        super(nameOfMedicine, groupOfMedicine, manufacturer, analogs, version, form);
        this.modified = modified;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
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
        Capsule medicine = (Capsule) o;

        return modified == medicine.modified;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return 31 * result + (modified ? 1 : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Capsule{");
        sb.append(super.toString());
        sb.append("modified=").append(modified);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Capsule capsule;

        private Builder() {
            capsule = new Capsule();
        }

        public Builder nameOfMedicineSet(String nameOfMedicine) {
            capsule.setNameOfMedicine(nameOfMedicine);
            return this;
        }

        public Builder groupOfMedicineSet(String groupOfMedicine) {
            capsule.setGroupOfMedicine(groupOfMedicine);
            return this;
        }

        public Builder manufacturerSet(Manufacturer manufacturer) {
            capsule.setManufacturer(manufacturer);
            return this;
        }

        public Builder analogsSet(String analogs) {
            capsule.setAnalogs(analogs);
            return this;
        }

        public Builder versionSet(Version version) {
            capsule.setVersion(version);
            return this;
        }

        public Builder formSet(Form form) {
            capsule.setForm(form);
            return this;
        }

        public Builder modifiedSet(boolean modified) {
            capsule.setModified(modified);
            return this;
        }

        public Capsule build() {
            return capsule;
        }
    }
}
