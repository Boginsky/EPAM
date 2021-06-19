package by.boginsky.entity;

public abstract class AbstractCommonMedicine {

    private String nameOfMedicine;
    private String groupOfMedicine;
    private Manufacturer manufacturer;
    private String analogs;
    private Version version;
    private Form form;

    AbstractCommonMedicine() {
    }

    public AbstractCommonMedicine(String nameOfMedicine, String groupOfMedicine, Manufacturer manufacturer, String analogs, Version version, Form form) {
        this.nameOfMedicine = nameOfMedicine;
        this.groupOfMedicine = groupOfMedicine;
        this.manufacturer = manufacturer;
        this.analogs = analogs;
        this.version = version;
        this.form = form;
    }

    public String getNameOfMedicine() {
        return nameOfMedicine;
    }

    public void setNameOfMedicine(String nameOfMedicine) {
        this.nameOfMedicine = nameOfMedicine;
    }

    public String getGroupOfMedicine() {
        return groupOfMedicine;
    }

    public void setGroupOfMedicine(String groupOfMedicine) {
        this.groupOfMedicine = groupOfMedicine;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAnalogs() {
        return analogs;
    }

    public void setAnalogs(String analogs) {
        this.analogs = analogs;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AbstractCommonMedicine medicine = (AbstractCommonMedicine) o;
        return nameOfMedicine.equals(medicine.nameOfMedicine) && groupOfMedicine.equals(medicine.groupOfMedicine)
                && manufacturer.equals(medicine.manufacturer) && analogs.equals(medicine.analogs)
                && version.equals(medicine.version) && form.equals(medicine.form);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((nameOfMedicine == null) ? 0 : nameOfMedicine.hashCode());
        result = 31 * result + ((groupOfMedicine == null) ? 0 : groupOfMedicine.hashCode());
        result = 31 * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = 31 * result + ((analogs == null) ? 0 : analogs.hashCode());
        result = 31 * result + ((version == null) ? 0 : version.hashCode());
        result = 31 * result + (((form) == null) ? 0 : form.hashCode());
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonMedicine{");
        sb.append("nameOfMedicine='").append(nameOfMedicine).append('\'');
        sb.append(", groupOfMedicine='").append(groupOfMedicine).append('\'');
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", analogs='").append(analogs).append('\'');
        sb.append(", version=").append(version);
        sb.append(", form=").append(form);
        sb.append('}');
        return sb.toString();
    }
}
