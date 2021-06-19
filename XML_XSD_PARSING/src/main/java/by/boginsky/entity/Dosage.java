package by.boginsky.entity;

public class Dosage {

    private float dosageOfMedicine;
    private int frequencyOfAdmission;

    public Dosage() {
    }

    public Dosage(float dosageOfMedicine, int frequencyOfAdmission) {
        this.dosageOfMedicine = dosageOfMedicine;
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    public float getDosageOfMedicine() {
        return dosageOfMedicine;
    }

    public void setDosageOfMedicine(float dosageOfMedicine) {
        this.dosageOfMedicine = dosageOfMedicine;
    }

    public int getFrequencyOfAdmission() {
        return frequencyOfAdmission;
    }

    public void setFrequencyOfAdmission(int frequencyOfAdmission) {
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Dosage dosage = (Dosage) o;
        if (dosageOfMedicine != dosage.dosageOfMedicine) {
            return false;
        }
        if (frequencyOfAdmission != dosage.frequencyOfAdmission) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) dosageOfMedicine;
        result = 31 * result + frequencyOfAdmission;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dosage{");
        sb.append("DosageOfMedicine=").append(dosageOfMedicine);
        sb.append(", frequencyOfAdmission=").append(frequencyOfAdmission);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Dosage dosage;

        private Builder() {
            dosage = new Dosage();
        }

        public Builder dosageOfMedicineSet (float dosageOfMedicine) {
            dosage.setDosageOfMedicine(dosageOfMedicine);
            return this;
        }

        public Builder frequencyOfAdmissionSet(int frequencyOfAdmission){
            dosage.setFrequencyOfAdmission(frequencyOfAdmission);
            return this;
        }

        public Dosage build() {
            return dosage;
        }
    }
}
